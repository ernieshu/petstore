package com.iwd.petstore_services;

import com.iwd.petstore_services.domain.Pet;

public interface PetStoreService {

	public Pet get(Long petId);

	public void add(Pet petToBeInserted);

	public void delete(Long petId);

}
