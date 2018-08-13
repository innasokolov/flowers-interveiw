package com.one800flowers.interview.service;

import com.one800flowers.interview.model.Json;
import com.one800flowers.interview.util.DataSupplier;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

public class FlowerServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testUniqueUserIds() throws Exception {
        FlowerService flowerService = new FlowerServiceImpl();
        List<Json> data = DataSupplier.data();
        Assert.assertEquals(10, flowerService.uniqueUserIds(data));
        //reducing the data such that two unique user IDs are removed
        data = data.subList(21, 99);
        Assert.assertEquals(8, flowerService.uniqueUserIds(data));
    }

    @Test
    public void testModify() throws Exception {
        List<Json> list = DataSupplier.data();
        Assert.assertEquals("nesciunt quas odio", list.get(4).getTitle());
        Assert.assertEquals("repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque", list.get(4).getBody());

        FlowerService flowerService = new FlowerServiceImpl();
        list = flowerService.modify(DataSupplier.data(), 5, "newTitle", "newBody");

        Assert.assertEquals("newTitle", list.get(4).getTitle());
        Assert.assertEquals("newBody", list.get(4).getBody());
    }

    @Test
    public void testModify_IndexOutOfBound() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Unable to update, not such element: 888");
        FlowerService flowerService = new FlowerServiceImpl();
        flowerService.modify(DataSupplier.data(), 888, "newTitle", "newBody");
    }
}
