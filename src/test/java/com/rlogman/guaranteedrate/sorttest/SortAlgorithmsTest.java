package com.rlogman.guaranteedrate.sorttest;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SortAlgorithmsTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void sortAlgoritihmsTest() {
    assertTrue("First comparator should be FemalesFirstThenLastNameComparator", SortAlgorithms.get(1) instanceof FemalesFirstThenLastNameComparator);
    assertTrue("Second comparator should be LastNameDescendingComparator", SortAlgorithms.get(2) instanceof BirthdayAscendingComparator);
    assertTrue("Third comparator should be BirthdayAscendingComparator", SortAlgorithms.get(3) instanceof LastNameDescendingComparator);
  }

  @Test
  public void invalidsortAlgoritihmsTest() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Invalid sort type (500); only 1, 2 and 3 are supported");
   SortAlgorithms.get(500);
  }
}
