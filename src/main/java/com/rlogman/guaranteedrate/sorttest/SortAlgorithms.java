package com.rlogman.guaranteedrate.sorttest;

import java.util.Comparator;

public class SortAlgorithms {

  public static final int BY_GENDER = 1;
  public static final int BY_BIRTHDAY = 2;
  public static final int BY_NAME = 3;

  public static Comparator<Person> get(int sortType) {
    switch (sortType) {
      case BY_GENDER:
        return new FemalesFirstThenLastNameComparator();
      case BY_BIRTHDAY:
        return new BirthdayAscendingComparator();
      case BY_NAME:
        return new LastNameDescendingComparator();
    }
    throw new IllegalArgumentException(
        String.format("Invalid sort type (%s); only 1, 2 and 3 are supported", sortType));
  }

}
