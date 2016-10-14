package com.rlogman.guaranteedrate.sorttest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class LineParser {

  public static final DateTimeFormatter DATE_OF_BIRTH_FORMAT = DateTimeFormatter.ofPattern("M/d/yyyy");

  public Person parse(String line, String separator) {
    String[] parts = line.split(separator);
    return Person.builder()
        .lastName(parts[0].trim())
        .firstName(parts[1].trim())
        .gender(Gender.valueOf(parts[2].trim().toUpperCase()))
        .favoriteColor(parts[3].trim())
        .dob(LocalDate.parse(parts[4].trim(), DATE_OF_BIRTH_FORMAT))
        .build();
  }

  private String findSeparator(String personLine) {
    if (personLine.indexOf(",") >= 0) {
      return ",";
    }
    if (personLine.indexOf("|") >= 0) {
      return "\\|";
    }
    if (personLine.indexOf(" ") >= 0) {
      return " ";
    }
    return null;
  }

  public Person parse(String personLine) {
    String separator = findSeparator(personLine);
    return (separator != null) ? parse(personLine, separator) : null;
  }

}
