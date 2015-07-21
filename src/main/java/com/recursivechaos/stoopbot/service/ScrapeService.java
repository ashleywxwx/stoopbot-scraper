/**
 * Created by Andrew Bell 7/20/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.service;

import com.recursivechaos.stoopbot.domain.Items;

import java.util.List;

public interface ScrapeService {
    List<Items> getNewItems();
}
