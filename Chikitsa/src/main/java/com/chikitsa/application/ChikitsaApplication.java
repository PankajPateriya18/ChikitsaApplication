package com.chikitsa.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChikitsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChikitsaApplication.class, args);
	}

}
