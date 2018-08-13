package com.one800flowers.interview.model;

import org.junit.Assert;
import org.junit.Test;

public class TallyTest {

    @Test
    public void testToString() {
        Tally tally = new Tally(666);
        Assert.assertEquals("Tally(uniqueUserIds=666)", tally.toString());

    }
}
