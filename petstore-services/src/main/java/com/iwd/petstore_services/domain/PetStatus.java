package com.iwd.petstore_services.domain;

public enum PetStatus {

	AVAILABLE("available"), 
	PENDING("pending"), 
	SOLD("sold");

	private final String statusName;
	
	PetStatus(String aStatusName) {
		this.statusName = aStatusName;
	}
	
	public String getStatusName() {
		return statusName;
	}
}
