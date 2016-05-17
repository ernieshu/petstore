package com.iwd.petstore_services.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PET_TAGS")
public class PetTag implements Serializable {

	@EmbeddedId
	private PetTagCompositeKey petTagCompositeKey;

	public PetTagCompositeKey getPetTagCompositeKey() {
		return petTagCompositeKey;
	}

	public void setPetTagCompositeKey(PetTagCompositeKey petTagCompositeKey) {
		this.petTagCompositeKey = petTagCompositeKey;
	}

}
