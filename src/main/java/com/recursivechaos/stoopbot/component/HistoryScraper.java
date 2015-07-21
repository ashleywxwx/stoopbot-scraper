/**
 * Created by Andrew Bell 7/12/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.component;

import com.recursivechaos.stoopbot.dao.ItemsDao;
import com.recursivechaos.stoopbot.domain.Items;
import com.recursivechaos.stoopbot.service.HistoryService;
import com.recursivechaos.stoopbot.service.RoomRequestService;
import com.recursivechaos.stoopbot.service.ScrapeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class HistoryScraper {

    private final Logger log = LoggerFactory.getLogger(HistoryScraper.class);

    @Autowired
    ItemsDao itemsDao;

    @Autowired
    ScrapeService scrapeService;

    @Autowired
    RoomRequestService roomRequestService;

    @Scheduled(fixedRate = 3000)
    public void getHistory() {

        List<Items> itemsList = roomRequestService.pollHistory();
        String latestPolledMessage = HistoryService.getLatestMessageId(itemsList);
        String latestStoredMessage = itemsDao.findAll(new Sort(Sort.Direction.DESC, "date")).get(0).getId();
        log.trace("Last polled id: " + latestPolledMessage);
        log.trace("Last stored id: " + latestStoredMessage);


        if (newItemsFound(latestPolledMessage, latestStoredMessage)) {
            String firstPulled = findNewMessagesAndReturnNewIndex(itemsList, latestStoredMessage);
            //lastStored = updateLastStored(firstPulled, lastStored);
        }

        log.debug("Done polling history.");
    }

    private String updateLastStored(String myFirstPulled, String myLastStored) {
        if (!myFirstPulled.equals("")) {
            myLastStored = myFirstPulled;
        }
        return myLastStored;
    }

    private String findNewMessagesAndReturnNewIndex(List<Items> itemsList, String myLastStored) {
        String firstPulled = "";
        int itemCount = itemsList.size();
        for (int i = itemCount - 1; i >= 0; i--) {
            Items currentItem = itemsList.get(i);
            if (firstPulled.equals("")) {
                firstPulled = currentItem.getId();
                log.debug("Saved first pulled: " + firstPulled);
            }
            if (currentItem.getId().equals(myLastStored)) {
                log.debug("All caught up");
                break;
            } else {
                itemsDao.save(currentItem);
                log.info("Pulled message: " + currentItem);
            }
        }
        return firstPulled;
    }

    private boolean newItemsFound(String latestMessageId, String lastStored) {
        boolean found = !lastStored.equals(latestMessageId);
        if (found) {
            log.info("New message(s) found");
        } else {
            log.debug("No new messages found.");
        }
        return found;
    }

}
