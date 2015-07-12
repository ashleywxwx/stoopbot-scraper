/**
 * Created by Andrew Bell 7/12/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    @Id
    String id;
    String date;
    String message;
    String type;
    From from;
    List<Mentions> mentions;
    @JsonProperty("message_format")
    String messageFormat;
    @JsonProperty("message_links")
    List<MessageLinks> messageLinks;

}
