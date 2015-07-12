/**
 * Created by Andrew Bell 7/12/2015
 * www.recursivechaos.com
 * andrew@recursivechaos.com
 * Licensed under MIT License 2015. See license.txt for details.
 */

package com.recursivechaos.dsbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DsbotHipchatApplication implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(DsbotHipchatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DsbotHipchatApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("Application started.");
    }
}
