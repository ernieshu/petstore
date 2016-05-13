package com.iwd.petstore_services;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iwd.petstore_services.domain.Pet;

@Component("petStoreService")
@Transactional
public class PetStoreServiceImpl implements PetStoreService {

	public Pet get(Long petId) {
		Pet returnPet = new Pet();
		returnPet.setId(petId);

		return returnPet;
	}

	public void add(Pet petToBeInserted) {
		// TODO Auto-generated method stub

	}

	public void delete(Long petId) {
		// TODO Auto-generated method stub

	}
}
