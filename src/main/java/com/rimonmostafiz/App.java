package com.rimonmostafiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Rimon Mostafiz
 */
@SpringBootApplication
@ComponentScan({"com.rimonmostafiz"})
@EntityScan("com.rimonmostafiz.core.model")
@EnableJpaRepositories("com.rimonmostafiz.core.repositories")
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
