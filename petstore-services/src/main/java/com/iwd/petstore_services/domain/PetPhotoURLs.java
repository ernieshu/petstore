package com.iwd.petstore_services.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PetPhotoURLs implements Serializable {

	@EmbeddedId
	@Column(name = "PET_ID", nullable = false)
	private Integer petId;

	@Column(name = "PHOTOURL", nullable = false)
	private String photoURL;

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer i) {
		this.petId = i;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

}
