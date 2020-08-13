package com.boz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BasicDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicDemoApplication.class, args);
    }

}
