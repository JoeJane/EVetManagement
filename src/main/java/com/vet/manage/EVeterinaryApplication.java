package com.vet.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * E-Veterinary Application starter
 */
@SpringBootApplication
public class EVeterinaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EVeterinaryApplication.class, args);
		System.out.println("E-Veterinary Application is Started Succesfully");
	}
}
