package com.github.schoolconsoleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.github.schoolconsoleapp.repository")
public class SchoolConsoleAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolConsoleAppApplication.class, args);
    }
}
