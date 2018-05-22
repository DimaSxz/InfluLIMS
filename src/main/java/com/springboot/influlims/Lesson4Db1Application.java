package com.springboot.influlims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Lesson4Db1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson4Db1Application.class, args);
	}
}
