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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "hipchat.domain")
public class RoomRequestService {

    private String url;
    private String key;
    private String room;

    public List<Items> pollHistory() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity("http://" + url + ".hipchat.com/v2/room/" + room + "/history?auth_token=" + key, History.class).getBody().getItems();
    }

}
