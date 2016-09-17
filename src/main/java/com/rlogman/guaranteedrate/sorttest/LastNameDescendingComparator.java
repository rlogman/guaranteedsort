package com.rlogman.guaranteedrate.sorttest;

import java.util.Comparator;

public class LastNameDescendingComparator implements Comparator<Person> {
  @Override
  public int compare(Person p1, Person p2) {
    if (p1 == null) {
      return (p2 == null) ? 0 : -1;
    }
    if (p2 == null) {
      return 1;
    }
    if (p1.getLastName() == null) {
      return (p2.getLastName() == null) ? 0 : -1;
    }
    if (p2.getLastName() == null) {
      return 1;
    }
    return -p1.getLastName().compareTo(p2.getLastName());
  }
}
