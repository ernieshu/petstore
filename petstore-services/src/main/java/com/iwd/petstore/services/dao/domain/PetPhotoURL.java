package com.iwd.petstore.services.dao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PET_PHOTO_URLS")
public class PetPhotoURL implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "PET_ID", nullable = false)
	private Integer petId;

	@Column(name = "PHOTOURL", nullable = false)
	private String photoURL;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
