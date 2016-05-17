package com.iwd.petstore_services;

import org.springframework.data.repository.Repository;

import com.iwd.petstore_services.domain.PetTag;
import com.iwd.petstore_services.domain.PetTagCompositeKey;

public interface PetTagRepository extends Repository<PetTag, Long> {

	PetTag findByPetTagCompositeKey(PetTagCompositeKey compositeKey);

	PetTag save(PetTag petTagTobeSaved);

	// void delete(Pet petToBeDeleted);

}
