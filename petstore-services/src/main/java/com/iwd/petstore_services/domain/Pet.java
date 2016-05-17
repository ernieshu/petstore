package com.iwd.petstore_services.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Pet implements Serializable {

	@Id
	// @GeneratedValue
	private Integer id;

	@Column
	private String name;

	@Column
	private String statusName;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	// @OneToMany(orphanRemoval = true)
	// @JoinColumn(name = "PET_ID")
	// private Set<PetPhotoURLs> photoURLs;
	//
	@ManyToMany
	@JoinTable(name = "PET_TAGS", joinColumns = @JoinColumn(name = "PET_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "TAG_ID"))
	private Set<Tag> tags;

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

	public PetStatus getStatus() {
		return PetStatus.getEnum(statusName);
	}

	public void setStatus(PetStatus status) {
		this.statusName = status.getStatusName();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// public Set<PetPhotoURLs> getPhotoURLs() {
	// return photoURLs;
	// }
	//
	// public void setPhotoURLs(Set<PetPhotoURLs> photoURLs) {
	// this.photoURLs = photoURLs;
	// }
	//
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
