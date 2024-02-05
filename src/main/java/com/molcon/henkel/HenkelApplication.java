package com.molcon.henkel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class HenkelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HenkelApplication.class, args);
	}

}
