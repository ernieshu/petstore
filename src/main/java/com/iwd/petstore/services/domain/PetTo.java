package com.iwd.petstore.services.domain;

import java.util.List;

import com.iwd.petstore.services.dao.domain.PetStatus;

public class PetTo {

	private Integer id;
	private String name;
	private CategoryTo category;
	private List<String> photoUrls;
	private List<TagTo> tags;
	private PetStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryTo getCategory() {
		return category;
	}

	public void setCategory(CategoryTo category) {
		this.category = category;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<TagTo> getTags() {
		return tags;
	}

	public void setTags(List<TagTo> tags) {
		this.tags = tags;
	}

	public PetStatus getStatus() {
		return status;
	}

	public void setStatus(PetStatus status) {
		this.status = status;
	}

}
