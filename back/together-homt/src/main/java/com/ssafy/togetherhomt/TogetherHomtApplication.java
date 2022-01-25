package com.ssafy.togetherhomt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TogetherHomtApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogetherHomtApplication.class, args);
	}

}
