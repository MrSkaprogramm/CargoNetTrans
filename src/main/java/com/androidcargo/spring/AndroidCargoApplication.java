package com.androidcargo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({
        "com.androidcargo.spring.models"
})
@EnableJpaRepositories("com.androidcargo.spring.repository")
public class AndroidCargoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AndroidCargoApplication.class, args);
    }
}
