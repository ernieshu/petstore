package com.iwd.petstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iwd.petstore.services.dao.domain.Pet;
import com.iwd.petstore.services.dao.domain.PetPhotoURL;
import com.iwd.petstore.services.dao.respositories.PetPhotoURLRepository;
import com.iwd.petstore.services.dao.respositories.PetRespository;

@Component("petStoreService")
@Transactional
public class PetStoreServiceImpl implements PetStoreService {

	private final PetRespository petRepository;
	private final PetPhotoURLRepository petPhotoUrlRepository;

	@Autowired
	public PetStoreServiceImpl(PetRespository petRepository, PetPhotoURLRepository petPhotoUrlRepository) {
		this.petRepository = petRepository;
		this.petPhotoUrlRepository = petPhotoUrlRepository;
	}

	@Override
	public Pet get(Integer petId) {
		Pet returnPet = petRepository.findOne(petId);
		List<PetPhotoURL> petPhotoUrls = petPhotoUrlRepository.findByPetId(petId);
		returnPet.setPhotoURLs(petPhotoUrls);
		return returnPet;
	}

	@Override
	public Pet add(Pet petToBeInserted) {

		// commit the pet
		Pet pet = petRepository.save(petToBeInserted);

		// commit any pet photo URLs
		if (petToBeInserted.getPhotoURLs()!=null) {
			for (PetPhotoURL petPhotoUrl : petToBeInserted.getPhotoURLs()) {
				petPhotoUrl.setPetId(pet.getId());
				petPhotoUrlRepository.save(petPhotoUrl);
			}
		}

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
