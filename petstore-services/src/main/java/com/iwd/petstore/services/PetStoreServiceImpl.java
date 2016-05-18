package com.iwd.petstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iwd.petstore.services.dao.domain.Pet;
import com.iwd.petstore.services.dao.respositories.PetPhotoURLRepository;
import com.iwd.petstore.services.dao.respositories.PetRespository;
import com.iwd.petstore.services.dao.respositories.PetTagRepository;

@Component("petStoreService")
@Transactional
public class PetStoreServiceImpl implements PetStoreService {

	private final PetRespository petRepository;
	private final PetTagRepository petTagRepository;
	private final PetPhotoURLRepository petPhotoUrlRepository;

	@Autowired
	public PetStoreServiceImpl(PetRespository petRepository, PetTagRepository petTagRepository,
			PetPhotoURLRepository petPhotoUrlRepository) {
		this.petRepository = petRepository;
		this.petTagRepository = petTagRepository;
		this.petPhotoUrlRepository = petPhotoUrlRepository;
	}

	@Override
	public Pet get(Integer petId) {
		return petRepository.findOne(petId);
	}

	@Override
	public Pet add(Pet petToBeInserted) {

		// commit the pet
		Pet pet = petRepository.save(petToBeInserted);

		// commit any pet photo URLs
		// for (PetPhotoURL petPhotoUrl : petToBeInserted.getPhotoURLs()) {
		// petPhotoUrlRepository.save(petPhotoUrl);
		// }

		if (pet != null) {
			return pet;
		} else {
			return null;
		}
	}

	@Override
	public void delete(Pet petToBeDeleted) {
		petRepository.delete(petToBeDeleted);
	}

}
