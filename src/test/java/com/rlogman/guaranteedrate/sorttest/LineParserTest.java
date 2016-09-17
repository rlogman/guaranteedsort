package com.rlogman.guaranteedrate.sorttest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LineParserTest {
  private static final String VALID_TEST_RECORD_SPACE = "Lopez Rodrigo Male Blue 12/2/1972";
  private static final String VALID_TEST_RECORD_PIPE = "Lopez | Rodrigo | Male | Blue | 12/2/1972";
  private static final String VALID_TEST_RECORD_COMMA = "Lopez, Rodrigo, Male, Blue, 12/2/1972";
  private static final String WRONG_GENDER_TEST_RECORD_COMMA = "Sanchez, Carolina, F, Blue, 6/7/1982";
  private static final String WRONG_DATE_TEST_RECORD_COMMA = "Sanchez, Carolina, Female, Blue, 25/12/1982";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Autowired
  private LineParser lineParser;

  @Test
  public void parseLineWithSpace() {
    Person person = lineParser.parse(VALID_TEST_RECORD_SPACE, " ");
    assertOnValidRecord(person);
  }
  
  @Test
  public void parseLineWithPipe() {
    Person person = lineParser.parse(VALID_TEST_RECORD_PIPE, "\\|");
    assertOnValidRecord(person);
  }
  
  @Test
  public void parseLineWithComma() {
    Person person = lineParser.parse(VALID_TEST_RECORD_COMMA, ",");
    assertOnValidRecord(person);
  }

  private void assertOnValidRecord(Person person) {
    assertNotNull("A correct line did not parse as a person", person);
    assertEquals("The last name was not parsed correctly", "Lopez", person.getLastName());
    assertEquals("The first name was not parsed correctly", "Rodrigo", person.getFirstName());
    assertEquals("The gender was not parsed correctly", Gender.MALE, person.getGender());
    assertEquals("The faviorite color was not parsed correctly", "Blue", person.getFavoriteColor());
    assertEquals("The date of birth was not parsed correctly", LocalDate.of(1972, 12, 2), person.getDob());
  }

  @Test
  public void parseLineWithWrongGender() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("No enum constant com.rlogman.guaranteedrate.sorttest.Gender.F");
    lineParser.parse(WRONG_GENDER_TEST_RECORD_COMMA, ",");
  }

  @Test
  public void parseLineWithWrongDate() {
    thrown.expect(DateTimeParseException.class);
    thrown.expectMessage("Text '25/12/1982' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 25");
    lineParser.parse(WRONG_DATE_TEST_RECORD_COMMA, ",");
  }

}
