package com.iwd.petstore.services.dao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PetTagCompositeKey implements Serializable {

	@Column(name = "PET_ID", nullable = false)
	private Integer petId;

	@Column(name = "TAG_ID", nullable = false)
	private Integer tagId;

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

}
