package com.example.cactus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CactusApplication {

    public static void main(String[] args) {
        SpringApplication.run(CactusApplication.class, args);
    }

}
