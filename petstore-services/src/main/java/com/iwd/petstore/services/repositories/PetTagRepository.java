package com.iwd.petstore.services.repositories;

import org.springframework.data.repository.Repository;

import com.iwd.petstore.services.domain.PetTag;

public interface PetTagRepository extends Repository<PetTag, Long> {

	PetTag save(PetTag petTagTobeSaved);

}
