package com.iwd.petstore.services;

import com.iwd.petstore.services.dao.domain.Pet;

public interface PetStoreService {

	public Pet get(Integer petId);

	public Pet add(Pet petToBeInserted);

	public void delete(Pet petToBeDeleted);

}
