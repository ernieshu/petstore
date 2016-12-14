package com.iwd.petstore.services.dao.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.iwd.petstore.services.dao.domain.PetPhotoURL;

public interface PetPhotoURLRepository extends Repository<PetPhotoURL, Long> {

	PetPhotoURL save(PetPhotoURL petPhotoToBeSaved);

	List<PetPhotoURL> findByPetId(Integer petId);
}
