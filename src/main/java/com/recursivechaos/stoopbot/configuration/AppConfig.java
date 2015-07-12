/**
 * Created by Andrew Bell 7/12/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.stoopbot.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Data
@Configuration
@ConfigurationProperties(prefix = "db.mongo")
public class AppConfig {

    private String url;

    private String dbname;

    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(url);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), dbname);
    }

}
