package com.example.landroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LandRouteApplication {
    public static void main(String[] args) {
        SpringApplication.run(LandRouteApplication.class, args);
    }

}
