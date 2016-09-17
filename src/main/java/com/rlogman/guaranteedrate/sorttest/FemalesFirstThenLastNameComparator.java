package com.rlogman.guaranteedrate.sorttest;

import java.util.Comparator;

public class FemalesFirstThenLastNameComparator implements Comparator<Person> {
  @Override
  public int compare(Person p1, Person p2) {
    if (p1 == null) {
      return (p2 == null) ? 0 : 1;
    }
    if (p2 == null) {
      return -1;
    }
    if (p1.getGender() == null) {
      return (p2.getGender() == null) ? 0 : 1;
    }
    if (p2.getGender() == null) {
      return -1;
    }
    if (p1.getGender() == p2.getGender()) {
      return p1.getLastName().compareTo(p2.getLastName());
    }
    if (p1.getGender() == Gender.FEMALE) {
      return -1;
    }
    return 1;
  }
}
