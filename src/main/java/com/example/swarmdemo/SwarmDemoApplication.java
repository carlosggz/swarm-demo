package com.example.swarmdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class SwarmDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwarmDemoApplication.class, args);
    }

    @Value("${app.id}")
    private String id;

    @Bean
    CommandLineRunner runner() {
        return args -> log.info("Started with id {}", id);
    }
}
