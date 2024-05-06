package org.nye.progkorny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ProgkornyApplication{
    public static void main(String[] args) {
        SpringApplication.run(ProgkornyApplication.class, args);
    }

}
