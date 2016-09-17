package com.rlogman.guaranteedrate.sorttest;

import java.util.Comparator;

public class SortAlgorithms {

  public static Comparator<Person> get(int sortType) {
    switch (sortType) {
      case 1:
        return new FemalesFirstThenLastNameComparator();
      case 2:
        return new BirthdayAscendingComparator();
      case 3:
        return new LastNameDescendingComparator();
    }
    throw new IllegalArgumentException(
        String.format("Invalid sort type (%s); only 1, 2 and 3 are supported", sortType));
  }

}
