package com.xyzbooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.xyzbooks.repository")
@EntityScan("com.xyzbooks.model")
@SpringBootApplication
public class XyzBooksApplication {
    public static void main(String[] args) {
        SpringApplication.run(XyzBooksApplication.class, args);
    }
}
