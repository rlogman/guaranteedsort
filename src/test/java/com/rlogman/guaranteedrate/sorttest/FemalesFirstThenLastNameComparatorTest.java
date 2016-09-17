package com.rlogman.guaranteedrate.sorttest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class FemalesFirstThenLastNameComparatorTest extends BaseComparatorTest {
  
  private FemalesFirstThenLastNameComparator comparator = new FemalesFirstThenLastNameComparator();

  @Test
  public void testBirthdayAscendingComparator() {
    assertThat("G goes before S", comparator.compare(p1, p2), greaterThan(0));
    assertThat("Female goes before Male", comparator.compare(p2, p3), lessThan(0));
    assertThat("Male goes before Female", comparator.compare(p3, p2), greaterThan(0));
    assertThat("Equal values shown as different", comparator.compare(p4, p4), is(0));
  }

  @Test
  public void testBirthdayAscendingComparatorEdgeCases() {
    assertThat("Equal values shown as different", comparator.compare(null, null), is(0));
    assertThat("null goes before Female", comparator.compare(null, p1), greaterThan(0));
    assertThat("null goes after Female", comparator.compare(p2, null), lessThan(0));
    assertThat("Male goes before null", comparator.compare(p3, p5), lessThan(0));
    assertThat("Female goes after null", comparator.compare(p6, p2), greaterThan(0));
    assertThat("Equal values shown as different", comparator.compare(p5, p6), is(0));
  }
}
