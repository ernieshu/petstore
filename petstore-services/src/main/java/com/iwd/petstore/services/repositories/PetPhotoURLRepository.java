package com.iwd.petstore.services.repositories;

import org.springframework.data.repository.Repository;

import com.iwd.petstore.services.domain.PetPhotoURL;

public interface PetPhotoURLRepository extends Repository<PetPhotoURL, Long> {

	PetPhotoURL save(PetPhotoURL petPhotoToBeSaved);

}
