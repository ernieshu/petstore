package com.iwd.petstore.services.dao.respositories;

import org.springframework.data.repository.Repository;

import com.iwd.petstore.services.dao.domain.PetTag;

public interface PetTagRepository extends Repository<PetTag, Long> {

	PetTag save(PetTag petTagTobeSaved);

}
