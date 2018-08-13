package com.one800flowers.interview.model;

import org.junit.Assert;
import org.junit.Test;

public class JsonTest {

    @Test
    public void testToString() {
        Json json = new Json(2, 345, "title", "body");
        Assert.assertEquals("Json(id=2, userId=345, title=title, body=body)", json.toString());

    }
}
