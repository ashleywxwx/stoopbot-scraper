/**
 * Created by Andrew Bell 7/12/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.dsbot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    @Id
    String id;
    String date;
    String message;
    String type;
    @ManyToOne(cascade = CascadeType.ALL)
    From from;
    @OneToMany
    @JoinColumn(name="id")
    List<Mentions> mentions;
    @JsonProperty("message_format")
    String messageFormat;
    @JsonProperty("message_links")
    @OneToMany
    @JoinColumn(name="pk")
    List<MessageLinks> messageLinks;

}
