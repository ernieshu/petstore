package com.iwd.petstore.services;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwd.petstore.services.dao.domain.Pet;
import com.iwd.petstore.services.domain.PetTo;
import com.iwd.petstore.services.util.ConversionUtils;

@SpringBootApplication
@ComponentScan
public class AppController {



	public static void main(String[] args) {
		SpringApplication.run(AppController.class, args);
	}


}
