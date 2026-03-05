package com.popruzhenko.hedgedrequests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HedgedRequestsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HedgedRequestsApplication.class, args);
    }

}
