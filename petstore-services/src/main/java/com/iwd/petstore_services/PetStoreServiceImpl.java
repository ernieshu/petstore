package com.iwd.petstore_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iwd.petstore_services.domain.Pet;

@Component("petStoreService")
@Transactional
public class PetStoreServiceImpl implements PetStoreService {

	private final PetRespository petRepository;

	@Autowired
	public PetStoreServiceImpl(PetRespository aPetRepository) {
		petRepository = aPetRepository;
	}

	public Pet get(Long petId) {
		return petRepository.findOne(petId);
	}

	public void add(Pet petToBeInserted) {
		Pet pet = petRepository.save(petToBeInserted);

		if (pet != null) {
			// TODO do something!
		} else {
			// TODO this is an error - do something else!
		}
	}

	public void delete(Long petId) {

		Pet petToBeDeleted = petRepository.findOne(petId);

		if (petToBeDeleted!=null) {
			petRepository.delete(petToBeDeleted);
		}
		else {
			// TODO - put an error log here!
		}
	}
}
