package com.iwd.petstore.services.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class Tag implements Serializable {

	@Id
	@Column(name = "TAG_ID")
	private Integer id;

	@Column(name = "TAG_NAME")
	private String name;

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

}
