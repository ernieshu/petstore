package com.iwd.petstore.services.dao.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Pet implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String name;

	@Column
	private String statusName;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@Transient
	private List<PetPhotoURL> photoURLs;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "PET_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "TAG_ID"))
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
		if (statusName != null) {
			return Enum.valueOf(PetStatus.class, statusName);
		} else {
			return null;
		}
	}

	public void setStatus(PetStatus status) {
		this.statusName = status.toString();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<PetPhotoURL> getPhotoURLs() {
		return photoURLs;
	}

	public void setPhotoURLs(List<PetPhotoURL> photoURLs) {
		this.photoURLs = photoURLs;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
