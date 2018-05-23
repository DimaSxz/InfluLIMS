package com.springboot.influlims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InfluLIMS {

	public static void main(String[] args) {
		SpringApplication.run(InfluLIMS.class, args);
	}
}
