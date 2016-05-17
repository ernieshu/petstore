package com.iwd.petstore_services.domain;

public enum PetStatus {

	AVAILABLE("Available"), 
	PENDING("Pending"), 
	SOLD("Sold");

	private final String statusName;
	
	PetStatus(String aStatusName) {
		this.statusName = aStatusName;
	}
	
	public String getStatusName() {
		return statusName;
	}
}
