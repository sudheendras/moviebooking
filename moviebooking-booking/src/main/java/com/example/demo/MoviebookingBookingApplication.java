package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MoviebookingBookingApplication {

	private static final Logger logger = LoggerFactory.getLogger(MoviebookingBookingApplication.class);
	
	public static void main(String[] args) {
		logger.info("at begining - com.example.demo.MoviebookingBookingApplication.main(String[])");
		SpringApplication.run(MoviebookingBookingApplication.class, args);
		logger.info("at ending - com.example.demo.MoviebookingBookingApplication.main(String[])");
	}

}
