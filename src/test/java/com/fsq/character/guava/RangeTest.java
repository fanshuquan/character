package com.fsq.character.guava;

import com.google.common.collect.Range;
import org.junit.Test;

/**
 * Created by fan on 19/2/18.
 */
public class RangeTest {
    @Test
    public void t() {
        Range<Integer> integerRange = Range.closedOpen(1, 10);
        System.out.println("[1,10):" + integerRange);
    }
}
