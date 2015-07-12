/**
 * Created by Andrew Bell 7/12/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.dao;

import com.recursivechaos.stoopbot.domain.Items;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemsDao extends MongoRepository<Items, String> {

}
