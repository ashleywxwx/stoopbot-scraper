/**
 * Created by Andrew Bell 7/20/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.service;

import com.recursivechaos.stoopbot.domain.Items;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemsServiceTest {

    @Test
    public void testGetNewest() throws Exception {
        ItemsService itemsService = new ItemsService();
        Items items1 = new Items();
        items1.setId("1");
        Items items2 = new Items();
        items2.setId("2");
        Items items3 = new Items();
        items3.setId("3");
        Items items4 = new Items();
        items4.setId("4");
        List<Items> itemsList = Arrays.asList(items1, items2, items3, items4);

        List<Items> newItems = itemsService.getNewItems(itemsList, "2");

        assertEquals("Did not return newest messages(3)", "3", newItems.get(0).getId());
        assertEquals("Did not return newest messages(4)", "4", newItems.get(1).getId());
    }
}
