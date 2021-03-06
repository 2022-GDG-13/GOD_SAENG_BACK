package com.gdg.group13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Group13Application {

	public static void main(String[] args) {
		SpringApplication.run(Group13Application.class, args);
	}

}
