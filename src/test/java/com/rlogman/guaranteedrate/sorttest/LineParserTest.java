package com.rlogman.guaranteedrate.sorttest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;
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
    assertEquals("The gender was not parsed correctly", Gender.Male, person.getGender());
    assertEquals("The faviorite color was not parsed correctly", "Blue", person.getFavoriteColor());
    assertEquals("The date of birth was not parsed correctly", LocalDate.of(1972, 12, 2), person.getDob());
  }

}
