/**
 * Created by Andrew Bell 7/20/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.service;

import com.recursivechaos.stoopbot.domain.History;
import com.recursivechaos.stoopbot.domain.Items;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "hipchat.domain")
public class RoomRequestService {

    private final Logger log = LoggerFactory.getLogger(RoomRequestService.class);

    private String url;
    private String key;
    private String room;

    public List<Items> pollHistory() {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Polling room: " + room);
        ResponseEntity<History> responseEntity = restTemplate.getForEntity("http://" + url + ".hipchat.com/v2/room/" + room + "/history?auth_token=" + key, History.class);
        log.debug("Response received: {}", responseEntity);
        return responseEntity.getBody().getItems();
    }

}
