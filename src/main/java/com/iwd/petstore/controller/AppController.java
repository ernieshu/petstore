package com.iwd.petstore.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class AppController {

	public static void main(String[] args) {
		SpringApplication.run(AppController.class, args);
	}

}
