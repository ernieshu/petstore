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

	@Override
	public Pet get(Integer petId) {
		return petRepository.findOne((long) petId);
	}

	@Override
	public Pet add(Pet petToBeInserted) {

		Pet pet = petRepository.save(petToBeInserted);

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
