package com.rlogman.guaranteedrate.sorttest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LastNameDescendingComparatorTest extends BaseComparatorTest {
  private LastNameDescendingComparator comparator = new LastNameDescendingComparator();

  @Test
  public void testLastNameDescendingComparator() {
    assertThat("S goes before G", comparator.compare(p1, p2), lessThan(0));
    assertThat("L goes before G", comparator.compare(p2, p3), greaterThan(0));
    assertThat("Equal values shown as different", comparator.compare(p4, p4), is(0));
  }

  @Test
  public void testLastNameDescendingComparatorEdgeCases() {
    assertThat("Equal values shown as different", comparator.compare(null, null), is(0));
    assertThat("null goes before S", comparator.compare(null, p1), lessThan(0));
    assertThat("null goes after G", comparator.compare(p2, null), greaterThan(0));
    assertThat("L goes before null", comparator.compare(p3, p5), greaterThan(0));
    assertThat("G goes after nulli", comparator.compare(p6, p2), lessThan(0));
    assertThat("Equal values shown as different", comparator.compare(p5, p6), is(0));
  }
}
