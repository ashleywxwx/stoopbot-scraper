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
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ScrapeServiceImpl implements ScrapeService {

    private final Logger log = LoggerFactory.getLogger(ScrapeServiceImpl.class);

    @Autowired
    ItemsDao itemsDao;
    @Autowired
    ItemsService itemsService;
    @Autowired
    RoomRequestService roomRequestService;

    @Override
    public List<Items> getNewItems() {
        List<Items> polledItems = roomRequestService.pollHistory();
        String latestStoredMessage = itemsService.findNewestItemId();
        return itemsService.getNewItems(polledItems, latestStoredMessage);
    }

}
