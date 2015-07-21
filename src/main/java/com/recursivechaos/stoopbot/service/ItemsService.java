/**
 * Created by Andrew Bell 7/20/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.service;

import com.recursivechaos.stoopbot.dao.ItemsDao;
import com.recursivechaos.stoopbot.domain.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsService {

    private final Logger log = LoggerFactory.getLogger(ItemsService.class);

    @SuppressWarnings({"SpringJavaAutowiredMembersInspection", "SpringJavaAutowiringInspection"})
    @Autowired
    ItemsDao itemsDao;

    public String findNewestItemId() {
        return itemsDao.findAll(new Sort(Sort.Direction.DESC, "date")).get(0).getId();
    }

    public List<Items> getNewItems(List<Items> polledItems, String latestStoredMessageId) {
        List<Items> newItems = new ArrayList<>();
        int itemCount = polledItems.size();
        for (int i = itemCount - 1; i >= 0; i--) {
            Items currentItem = polledItems.get(i);
            if (currentItem.getId().equals(latestStoredMessageId)) {
                log.debug("All caught up");
                break;
            } else {
                log.info("New message: " + currentItem);
                newItems.add(0, currentItem);
            }
        }
        return newItems;
    }

    public void saveNewItems(List<Items> newItems) {
        if (null != newItems && newItems.size() > 0) {
            log.debug("Saving {} item(s) to db.", newItems.size());
            itemsDao.save(newItems);
        }
    }
}
