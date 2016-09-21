package com.rlogman.guaranteedrate.sorttest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CLI {
  @Autowired
  LineParser lineParser;

  @Autowired
  PersonStore personStore;

  private int sortType;
  private File pipeSeparatedFile;
  private File commaSeparatedFile;
  private File spaceSeparatedFile;

  public static void main(String... args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(PeopleSortApplication.class);
    ctx.refresh();
    CLI instance = new CLI();
    instance.processCommandLineArguments(args);
    instance.readFiles();
    instance.generateOutput();
    ctx.close();
  }

  private void readFiles() {
    readFile(pipeSeparatedFile, "\\|");
    readFile(commaSeparatedFile, ",");
    readFile(spaceSeparatedFile, " ");
  }

  private CommandLine processCommandLineArguments(String... args) {
    Option pipe = Option.builder("pipe").argName("pipeSeparatedFile").hasArg()
        .desc("input file using the pipe symbol as separator").required().build();
    Option comma = Option.builder("comma").argName("commaSeparatedFile").hasArg()
        .desc("input file using comma as separator").required().build();
    Option space = Option.builder("space").argName("spaceSeparatedFile").hasArg()
        .desc("input file using space as separator").required().build();
    Option sortBy = Option.builder("sortType").argName("sortType").hasArg()
        .type(Number.class)
        .desc("the type of sort expected for the output; "
            + "1 means sorted by gender (females before males) "
            + "then by last name ascending; "
            + "2 means sorted by birth date, ascending; "
            + "and 3 means sorted by last name, descending")
        .required().build();
    Options options = new Options();
    options.addOption(pipe).addOption(comma).addOption(space).addOption(sortBy);

    CommandLineParser parser = new DefaultParser();
    CommandLine line;
    try {
      line = parser.parse(options, args);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Error while parsing command line arguments." + options.toString());
    }
    List<String> errorMessages = new ArrayList<>(3);
    this.sortType = Integer.parseInt(line.getOptionValue("sortType"));
    if ((sortType < 1) || (sortType > 3)) {
      errorMessages.add(String.format(
          "Invalid sort type (%s); only 1, 2 and 3 are supported", sortType));
    }
    this.pipeSeparatedFile = validateFileArgument("pipe", line, errorMessages);
    this.commaSeparatedFile = validateFileArgument("comma", line, errorMessages);
    this.spaceSeparatedFile = validateFileArgument("space", line, errorMessages);
    if (errorMessages.size() > 0) {
      throw new IllegalArgumentException("Problems with command line arguments: " + errorMessages.toString());
    }
    return line;
  }

  private File validateFileArgument(String opt, CommandLine line, List<String> errorMessages) {
    String optValue = line.getOptionValue(opt);
    File result = new File(optValue);
    if (!result.exists()) {
      errorMessages.add(String.format(
          "'%s' file (%s) does not exist", opt, optValue));
    }
    return result;
  }

  private void generateOutput() {
    List<Person> people = personStore.getOrderedPeople(SortAlgorithms.get(this.sortType));
    System.out.println("LastName FirstName Gender FavoriteColor DateOfBirth");
    for (Person person : people) {
      System.out.printf("%s %s %s %s %s%n",
          person.getLastName(), person.getFirstName(), person.getGender(),
          person.getFavoriteColor(),
          LineParser.DATE_OF_BIRTH_FORMAT.format(person.getDob()));
    }
  }

  private void readFile(File file, String separator) {
    String line = null;
    BufferedReader in = null;
    try {
      in = new BufferedReader(new FileReader(file));

      while (((line = in.readLine()) != null) && !"".equals(line.length())) {
        Person person = lineParser.parse(line, separator);
        personStore.add(person);
      }
    } catch (IOException ex) {
      log.error("I/O error", ex);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          log.error("I/O error while closing connection to source file", e);
        }
      }
    }
  }

}
