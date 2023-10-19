package com.example.traveliouserreplica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class TravelioUserReplicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelioUserReplicaApplication.class, args);
    }

}
