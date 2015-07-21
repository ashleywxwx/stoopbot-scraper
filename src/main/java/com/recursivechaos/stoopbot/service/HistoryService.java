/**
 * Created by Andrew Bell 7/20/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.service;

import com.recursivechaos.stoopbot.domain.Items;

import java.util.List;

public class HistoryService {

    public static String getLatestMessageId(List<Items> itemsList) {
        return itemsList.get(itemsList.size() - 1).getId();
    }

}
