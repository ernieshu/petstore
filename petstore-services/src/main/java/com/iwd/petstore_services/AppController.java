package com.iwd.petstore_services;

import java.util.HashSet;
import java.util.Set;

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

import com.iwd.petstore_services.domain.Category;
import com.iwd.petstore_services.domain.Pet;
import com.iwd.petstore_services.domain.PetPhotoURLs;
import com.iwd.petstore_services.domain.PetStatus;
import com.iwd.petstore_services.domain.Tag;

@Controller
@SpringBootApplication
public class AppController {

	@Autowired
	PetStoreService petStoreService;

	@RequestMapping(value = "/pet/{petId}", method = { RequestMethod.GET })
	@ResponseBody
	@Transactional(readOnly = true)
	ResponseEntity<Pet> get(@PathVariable Integer petId) {
		if (petId < 0) {
			return new ResponseEntity<Pet>(HttpStatus.BAD_REQUEST);
		}

		Pet returnPet = petStoreService.get(petId);
		if (returnPet == null) {
			return new ResponseEntity<Pet>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Pet>(returnPet, HttpStatus.OK);
	}

	@RequestMapping(value = "/pet", method = { RequestMethod.POST })
	@ResponseBody
	ResponseEntity<Pet> create(Pet pet) {

		// FIXME - below code is only for testing purposes

		pet.setId(1);
		Category category = new Category();
		category.setId(1);
		category.setName("Category 1");
		pet.setCategory(category);
		pet.setName("doggie");
		PetPhotoURLs petPhotoUrl = new PetPhotoURLs();
		petPhotoUrl.setPetId(pet.getId());
		petPhotoUrl.setPhotoURL("string");
		Set<PetPhotoURLs> photoSet = new HashSet<PetPhotoURLs>();
		photoSet.add(petPhotoUrl);
		// pet.setPhotoURLs(photoSet);
		Tag tag = new Tag();
		tag.setId(1);
		tag.setName("TAG 1");
		Set<Tag> tagSet = new HashSet<Tag>();
		tagSet.add(tag);
		pet.setTags(tagSet);
		pet.setStatus(PetStatus.AVAILABLE);

		if (!validatePet(pet)) {
			return new ResponseEntity<Pet>(HttpStatus.BAD_REQUEST);
		} else {

			Pet returnedPet = petStoreService.add(pet);
			System.err.println(returnedPet.getStatus());
			if (returnedPet == null) {
				return new ResponseEntity<Pet>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<Pet>(returnedPet, HttpStatus.OK);
			}
		}

	}

	@RequestMapping(value = "/pet/{petId}", method = { RequestMethod.DELETE })
	@ResponseBody
	ResponseEntity<String> delete(@PathVariable Integer petId) {
		if (petId < 0) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		Pet returnPet = petStoreService.get(petId);
		if (returnPet == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		petStoreService.delete(returnPet);

		return new ResponseEntity<String>(HttpStatus.OK);
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

	private boolean validatePet(Pet pet) {
		if (pet.getName() == null || pet.getName().length() == 0) {
			return false;
		}
		// if (pet.getPhotoURLs().size() == 0) {
		// return false;
		// }
		return true;
	}

}
