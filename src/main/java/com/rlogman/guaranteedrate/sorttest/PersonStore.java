package com.rlogman.guaranteedrate.sorttest;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PersonStore {

  private static List<Person> people = new LinkedList<>();
  
  public void add(Person person) {
    people.add(person);
  }

  public List<Person> getOrderedPeople(Comparator<Person> comparator) {
    Collections.sort(people, comparator);
    return people;
  }

}
