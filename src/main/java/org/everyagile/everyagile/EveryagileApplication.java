package org.everyagile.everyagile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EveryagileApplication {

    public static void main(String[] args) {
        SpringApplication.run(EveryagileApplication.class, args);
    }

}
