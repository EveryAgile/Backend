package org.everyagile.everyagile.everyagile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableAutoConfiguration
@EnableJpaAuditing
//	(exclude = {DataSourceAutoConfiguration.class})
public class EveryAgileApplication {

    public static void main(String[] args) {
        SpringApplication.run(EveryAgileApplication.class, args);
    }

}
