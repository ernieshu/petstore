package com.iwd.petstore_services;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	ResponseEntity<String> get(@PathVariable Long petId) {
		if (petId < 0) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		Pet returnPet = petStoreService.get(petId);
		if (returnPet == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}  
		
		return new ResponseEntity<String>(returnPet.toString(), HttpStatus.ACCEPTED); //FIXME use JSON rather than toString
	}

	@RequestMapping(value = "/pet", method = { RequestMethod.POST })
	@ResponseBody
	ResponseEntity<String> create(Pet pet) {
		
		if (!validatePet(pet)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		else {
			petStoreService.add(pet);
			return new ResponseEntity<String>(pet.toString(), HttpStatus.ACCEPTED); //FIXME this should be sending JSON rather than toString representation
		}
		
		
	}

	@RequestMapping(value = "/pet/{petId}", method = { RequestMethod.DELETE })
	@ResponseBody
	ResponseEntity<String> delete(@PathVariable Long petId) {
		if (petId == 0) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		Pet returnPet = petStoreService.get(petId);
		if (returnPet == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}  

		petStoreService.delete(petId);
		
		return new ResponseEntity<String>(HttpStatus.ACCEPTED); //FIXME use JSON rather than toString
	}

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppController.class, args);
	}
	
	private boolean validatePet(Pet pet) {
		if (pet.getName() == null || pet.getName().length() == 0) {
			return false;
		}
		if (pet.getPhotoURLs().size()==0) {
			return false;
		}
		return true;
	}
}
