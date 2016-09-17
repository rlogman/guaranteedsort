package com.rlogman.guaranteedrate.sorttest;

import java.time.LocalDate;

public class BaseComparatorTest {

  protected Person p1 = Person.builder().lastName("Sanchez").gender(Gender.FEMALE)
      .dob(LocalDate.of(1982, 6, 7)).build();
  protected Person p2 = Person.builder().lastName("Guzman").gender(Gender.FEMALE)
      .firstName("Escilida")
      .dob(LocalDate.of(1941, 3, 24))
      .build();
  protected Person p3 = Person.builder().lastName("Lopez").gender(Gender.MALE)
      .dob(LocalDate.of(1972, 12, 2))
      .build();
  protected Person p4 = Person.builder().lastName("Rodriguez").gender(Gender.MALE)
      .dob(LocalDate.of(1995, 7, 29))
      .build();
  protected Person p5 = Person.builder().build();
  protected Person p6 = Person.builder().build();
}
