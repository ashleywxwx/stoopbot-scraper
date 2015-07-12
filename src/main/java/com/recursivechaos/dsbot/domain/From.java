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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class From {

    @Id
    Long id;
    @ManyToOne
    @JoinColumn(name="self")
    Links links;
    @JsonProperty("mention_name")
    String mentionName;
    String name;

    public From(String name) {
        this.name = name;
    }

    public From() {
    }

}
