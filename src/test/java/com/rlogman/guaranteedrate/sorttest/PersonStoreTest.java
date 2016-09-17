package com.rlogman.guaranteedrate.sorttest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonStoreTest {

  private static PersonStore store = new PersonStore();

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @BeforeClass
  public static void setup() {
    store.add(new Person("Lopez", "Rodrigo", Gender.MALE, "Blue", LocalDate.of(1972, 12, 2)));
    store.add(new Person("Lopez", "Camila", Gender.MALE, "Pink", LocalDate.of(2005, 12, 19)));
    store.add(new Person("Sanchez", "Carolina", Gender.FEMALE, "Yellow", LocalDate.of(1982, 6, 7)));
    store.add(new Person("Jaramillo", "Sara", Gender.FEMALE, "Purple", LocalDate.of(2001, 12, 24)));
    store.add(new Person("Lopez", "Jacob", Gender.MALE, "Blue", LocalDate.of(2016, 7, 27)));
    store.add(new Person("Lopez", "Benjamin", Gender.MALE, "Blacik", LocalDate.of(2016, 7, 27)));
  }

  @Test
  public void sortByFemalesFirstThenLastName() {
    List<Person> orderedPeople = store.getOrderedPeople(SortAlgorithms.get(1));
    assertEquals("Females must go first", Gender.FEMALE, orderedPeople.get(0).getGender());
    assertEquals("Second level of sorting is last name", "Jaramillo", orderedPeople.get(0).getLastName());
    assertEquals("Males must go last", Gender.MALE, orderedPeople.get(5).getGender());
    assertEquals("Second level of sorting is last name", "Lopez", orderedPeople.get(5).getLastName());
  }

  @Test
  public void sortByLastNameDescending() {
    List<Person> orderedPeople = store.getOrderedPeople(SortAlgorithms.get(3));
    assertEquals("Last alphabetical last name should appear first", "Sanchez", orderedPeople.get(0).getLastName());
    assertEquals("First alphabetical last name should appear last", "Jaramillo", orderedPeople.get(5).getLastName());
  }

  @Test
  public void sortByBirthdayAscending() {
    List<Person> orderedPeople = store.getOrderedPeople(SortAlgorithms.get(2));
    assertEquals("Oldest person should appear first", "Rodrigo", orderedPeople.get(0).getFirstName());
    assertEquals("Youngest person should appear last", LocalDate.of(2016, 7, 27), orderedPeople.get(5).getDob());
  }

}
