package com.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourseExchangeOfThingsApplication{
	public static void main(String[] args) {
		SpringApplication.run(CourseExchangeOfThingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("Start project: CourseExchangeOfThingsApplication has started successfully!");
		};
	}
}
