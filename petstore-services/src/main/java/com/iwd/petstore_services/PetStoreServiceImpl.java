package com.iwd.petstore_services;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iwd.petstore_services.domain.Pet;
import com.iwd.petstore_services.domain.PetTag;
import com.iwd.petstore_services.domain.PetTagCompositeKey;
import com.iwd.petstore_services.domain.Tag;

@Component("petStoreService")
@Transactional
public class PetStoreServiceImpl implements PetStoreService {

	private final PetRespository petRepository;
	private final PetTagRepository petTagRepository;

	@Autowired
	public PetStoreServiceImpl(PetRespository aPetRepository, PetTagRepository pettagRepository) {
		petRepository = aPetRepository;
		petTagRepository = pettagRepository;
	}

	@Override
	public Pet get(Integer petId) {
		return petRepository.findOne(petId);
	}

	@Override
	public Pet add(Pet petToBeInserted) {

		// commit the pet
		Pet pet = petRepository.save(petToBeInserted);

		// commit any pet/tag relationships
		Iterator iter = petToBeInserted.getTags().iterator();
		while (iter.hasNext()) {
			Tag tag = (Tag) iter.next();
			PetTag petTagToBeInserted = new PetTag();
			PetTagCompositeKey petTagCompositeKey = new PetTagCompositeKey();
			petTagCompositeKey.setPetId(pet.getId());
			petTagCompositeKey.setTagId(tag.getId());
			petTagToBeInserted.setPetTagCompositeKey(petTagCompositeKey);
			petTagRepository.save(petTagToBeInserted);
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
