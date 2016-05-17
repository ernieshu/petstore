package com.iwd.petstore_services.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pet implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;
	
	@Column
	private PetStatus status;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CATEGORY_ID")
	private Category category;
	
	// TODO phtotoURLS
	@OneToMany(orphanRemoval=true)
	@JoinColumn(name="PET_ID")
	private Set<String> photoURLs;
	
    @ManyToMany
    @JoinTable(name="PET_TAGS",
        joinColumns=
            @JoinColumn(name="PET_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="TAG_ID", referencedColumnName="TAG_ID")
        )
	private Set<Tag> tags;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public PetStatus getStatus() {
		return status;
	}

	public void setStatus(PetStatus status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<String> getPhotoURLs() {
		return photoURLs;
	}

	public void setPhotoURLs(Set<String> photoURLs) {
		this.photoURLs = photoURLs;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
