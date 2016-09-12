package com.rlogman.guaranteedrate.sorttest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class LineParser {

  public Person parse(String line, String separator) {
    String[] parts = line.split(separator);
    return Person.builder()
        .lastName(parts[0].trim())
        .firstName(parts[1].trim())
        .gender(Gender.valueOf(parts[2].trim()))
        .favoriteColor(parts[3].trim())
        .dob(LocalDate.parse(parts[4].trim(), DateTimeFormatter.ofPattern("M/d/yyyy")))
        .build();
  }

}
