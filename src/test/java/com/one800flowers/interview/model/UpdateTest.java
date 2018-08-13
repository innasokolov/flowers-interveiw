package com.one800flowers.interview.model;

import org.junit.Assert;
import org.junit.Test;

public class UpdateTest {

    @Test
    public void testToString() {
        Update update = new Update(4, "title", "body");
        Assert.assertEquals("Update(element=4, title=title, body=body)", update.toString());

    }
}
