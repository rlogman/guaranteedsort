package com.rlogman.guaranteedrate.sorttest;

import java.util.Comparator;

public class BirthdayAscendingComparator implements Comparator<Person> {
  @Override
  public int compare(Person p1, Person p2) {
    if (p1 == null) {
      return (p2 == null) ? 0 : 1;
    }
    if (p2 == null) {
      return -1;
    }
    if (p1.getDob() == null) {
      return (p2.getDob() == null) ? 0 : 1;
    }
    if (p2.getDob() == null) {
      return -1;
    }
    return p1.getDob().compareTo(p2.getDob());
  }
}
