package com.rlogman.guaranteedrate.sorttest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LineParserTest {
  @Autowired
  private LineParser lineParser;
  
  @Test
  public void parseLineWithSpace() {
    Person person = lineParser.parse("Lopez Rodrigo Male Blue 12/2/1972", ' ');
    assertNotNull("A correct line did not parse as a person", person);
  }

}
