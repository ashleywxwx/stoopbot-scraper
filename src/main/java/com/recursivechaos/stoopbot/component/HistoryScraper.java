/**
 * Created by Andrew Bell 7/12/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.component;

import com.recursivechaos.stoopbot.domain.Items;
import com.recursivechaos.stoopbot.service.ItemsService;
import com.recursivechaos.stoopbot.service.ScrapeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class HistoryScraper {

    private final Logger log = LoggerFactory.getLogger(HistoryScraper.class);

    @Autowired
    ScrapeService scrapeService;

    @Autowired
    ItemsService itemsService;

    @Scheduled(fixedRate = 3000)
    public void getHistory() {
        List<Items> newItems = scrapeService.getNewItems();
        itemsService.saveNewItems(newItems);
        log.debug("Done polling history.");
    }


}
