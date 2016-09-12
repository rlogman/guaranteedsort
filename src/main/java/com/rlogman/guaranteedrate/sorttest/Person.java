package com.rlogman.guaranteedrate.sorttest;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
  private String lastName;
  private String firstName;
  private Gender gender;
  private String favoriteColor;
  private LocalDate dob;
}
