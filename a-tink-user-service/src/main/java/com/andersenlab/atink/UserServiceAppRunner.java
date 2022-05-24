package com.andersenlab.atink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class UserServiceAppRunner {
    public static void main(String[] args) {

        SpringApplication.run(UserServiceAppRunner.class,args);
    }
}
