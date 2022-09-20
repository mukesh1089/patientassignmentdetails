package com.patient.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableWebMvc
@SpringBootApplication
public class HealthDomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthDomainApplication.class, args);
	}

}
