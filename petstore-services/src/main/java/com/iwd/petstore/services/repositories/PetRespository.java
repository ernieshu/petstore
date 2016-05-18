package com.iwd.petstore.services.repositories;

import org.springframework.data.repository.Repository;

import com.iwd.petstore.services.domain.Pet;

public interface PetRespository extends Repository<Pet, Integer> {

	Pet findOne(Integer primaryKey);

	Pet save(Pet petTobeSaved);

	void delete(Pet petToBeDeleted);

}
