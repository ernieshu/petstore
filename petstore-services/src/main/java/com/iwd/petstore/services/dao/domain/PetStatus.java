package com.iwd.petstore.services.dao.domain;

public enum PetStatus {

	AVAILABLE("available"), PENDING("pending"), SOLD("sold");

	private final String statusName;

	PetStatus(String aStatusName) {
		this.statusName = aStatusName;
	}

	public String getStatusName() {
		return statusName;
	}

	public static PetStatus getEnum(String valueOf) {
		for (PetStatus v : values()) {
			if (v.getStatusName().equalsIgnoreCase(valueOf)) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}

}
