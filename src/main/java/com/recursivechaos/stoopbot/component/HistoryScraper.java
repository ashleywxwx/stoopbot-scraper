/**
 * Created by Andrew Bell 7/12/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.component;

import com.recursivechaos.stoopbot.dao.ItemsDao;
import com.recursivechaos.stoopbot.domain.History;
import com.recursivechaos.stoopbot.domain.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class HistoryScraper {

    private final Logger log = LoggerFactory.getLogger(HistoryScraper.class);
    public String lastStored = "";
    @Autowired
    ItemsDao itemsDao;
    RestTemplate restTemplate = new RestTemplate();
    String roomName = "Stoopbots";

    @Scheduled(fixedRate = 3000)
    public void getHistory() {

        ResponseEntity<History> responseEntity = pollHistory();
        checkRateLimit(responseEntity);
        String latestMessageId = getLatestMessageId(responseEntity);
        log.debug("Last stored id: " + lastStored);
        List<Items> itemsList = responseEntity.getBody().getItems();

        if (newItemsFound(latestMessageId, lastStored)) {
            String firstPulled = findNewMessagesAndReturnNewIndex(itemsList, lastStored);
            lastStored = updateLastStored(firstPulled, lastStored);
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

    private String getLatestMessageId(ResponseEntity<History> responseEntity) {
        String mid = responseEntity.getBody().getItems().get(responseEntity.getBody().getItems().size() - 1).getId();
        log.debug("Latest Message ID: " + mid);
        return mid;
    }

    private void checkRateLimit(ResponseEntity<History> responseEntity) {
        String rateRemaining = responseEntity.getHeaders().get("X-RateLimit-Remaining").toString();
        log.debug("Rate remaining: " + rateRemaining);
    }

    // Queries Hipchat for latest history
    private ResponseEntity<History> pollHistory() {
        log.debug("Polling history...");
        return restTemplate.getForEntity("http://stoopkids.hipchat.com/v2/room/" + roomName + "/history?auth_token=pwQwG9WNVu3RYMMVdpvChgcISJGqrasUpSzhPkYA", History.class);
    }

}
