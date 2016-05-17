package com.iwd.petstore_services;

import org.springframework.data.repository.Repository;

import com.iwd.petstore_services.domain.PetTag;

public interface PetTagRepository extends Repository<PetTag, Long> {

	PetTag save(PetTag petTagTobeSaved);

}
