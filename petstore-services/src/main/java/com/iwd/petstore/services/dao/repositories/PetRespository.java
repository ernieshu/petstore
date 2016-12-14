package com.iwd.petstore.services.dao.repositories;

import org.springframework.data.repository.Repository;

import com.iwd.petstore.services.dao.domain.Pet;

public interface PetRespository extends Repository<Pet, Integer> {

	Pet findOne(Integer primaryKey);

	Pet save(Pet petTobeSaved);

	void delete(Pet petToBeDeleted);

}
