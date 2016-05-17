package com.iwd.petstore_services;

import org.springframework.data.repository.Repository;

import com.iwd.petstore_services.domain.Pet;

public interface PetRespository extends Repository<Pet, Integer> {

	Pet findOne(Integer primaryKey);

	Pet save(Pet petTobeSaved);

	void delete(Pet petToBeDeleted);

}
