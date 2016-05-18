package com.iwd.petstore.services.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PET_PHOTO_URLS")
public class PetPhotoURL implements Serializable {

	@EmbeddedId
	private PetURLCompositeKey petURLCompositeKey;

	public PetURLCompositeKey getPetURLCompositeKey() {
		return petURLCompositeKey;
	}

	public void setPetURLCompositeKey(PetURLCompositeKey petURLCompositeKey) {
		this.petURLCompositeKey = petURLCompositeKey;
	}

}
