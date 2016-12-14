package com.iwd.petstore.services;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
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

@Controller
public class PetController {
	
	private final PetStoreService petStoreService;
	
	private final ConversionUtils conversionUtils;
	
	@Autowired
	public PetController(PetStoreService petStoreService, ConversionUtils conversionUtils) {
		this.petStoreService = petStoreService;
		this.conversionUtils = conversionUtils;
	}
	
	@RequestMapping(value = "/pet/{petId}", method = { RequestMethod.GET })
	@ResponseBody
	@Transactional(readOnly = true)
	ResponseEntity<PetTo> get(@PathVariable Integer petId) {
		if (petId < 0) {
			return new ResponseEntity<PetTo>(HttpStatus.BAD_REQUEST);
		}

		Pet pet = petStoreService.get(petId);

		if (pet == null) {
			return new ResponseEntity<PetTo>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PetTo>(conversionUtils.convert(pet), HttpStatus.OK);
	}

	@RequestMapping(value = "/pet", method = { RequestMethod.POST })
	ResponseEntity<PetTo> create(@RequestBody PetTo petTo) {

		if (!validatePet(petTo)) {
			return new ResponseEntity<PetTo>(HttpStatus.BAD_REQUEST);
		} else {

			Pet petToCommit = conversionUtils.convert(petTo);

			petToCommit = petStoreService.add(petToCommit);

			PetTo petToReturn = conversionUtils.convert(petToCommit);

			return new ResponseEntity<PetTo>(petToReturn, HttpStatus.OK);
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

	@Bean //FIXME - for demo purposes only
	public ServletRegistrationBean h2ServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}
	
	private boolean validatePet(PetTo pet) {
		if (pet.getName() == null || pet.getName().length() == 0) {
			return false;
		}
		return true;
	}
}
