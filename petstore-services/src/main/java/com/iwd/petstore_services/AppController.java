package com.iwd.petstore_services;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwd.petstore_services.domain.Pet;

@Controller
@SpringBootApplication
public class AppController {

	@Autowired
	PetStoreService petStoreService;

	@RequestMapping(value = "/pet/{petId}", method = { RequestMethod.GET })
	@ResponseBody
	@Transactional(readOnly = true)
	Pet get(@PathVariable Long petId) {
		return petStoreService.get(petId);
	}

	@RequestMapping(value = "/pet", method = { RequestMethod.POST })
	@ResponseBody
	void create(Pet pet) {
		petStoreService.add(pet);
	}

	@RequestMapping(value = "/pet/{petId}", method = { RequestMethod.DELETE })
	@ResponseBody
	void delete(@PathVariable Long petId) {
		petStoreService.delete(petId);
	}

	@Bean
	public ServletRegistrationBean h2ServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppController.class, args);
	}
}
