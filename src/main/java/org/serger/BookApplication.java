package org.serger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookApplication {

    private static final Logger log = LoggerFactory.getLogger(BookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@Bean
    CommandLineRunner runner() {
	    return args -> {
            log.debug("runner...");
        };
    }
}
