package com.iwd.petstore.services.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PetURLCompositeKey implements Serializable {

	@Column(name = "PET_ID", nullable = false)
	private Integer petId;

	@Column(name = "PHOTOURL", nullable = false)
	private String photoURL;

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
}
