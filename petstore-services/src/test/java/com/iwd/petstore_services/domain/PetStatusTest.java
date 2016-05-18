package com.iwd.petstore_services.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.iwd.petstore.services.domain.PetStatus;

public class PetStatusTest {

	@Test
	public void testGetValues_validCase() {

		PetStatus returnPetStatus = PetStatus.getEnum(PetStatus.AVAILABLE.getStatusName());
		assertEquals(PetStatus.AVAILABLE, returnPetStatus);

		// test case sensitive case
		returnPetStatus = PetStatus.getEnum(PetStatus.AVAILABLE.getStatusName().toUpperCase());
		assertEquals(PetStatus.AVAILABLE, returnPetStatus);

		returnPetStatus = PetStatus.getEnum(PetStatus.PENDING.getStatusName());
		assertEquals(PetStatus.PENDING, returnPetStatus);

		// test case sensitive case
		returnPetStatus = PetStatus.getEnum(PetStatus.PENDING.getStatusName().toUpperCase());
		assertEquals(PetStatus.PENDING, returnPetStatus);

		returnPetStatus = PetStatus.getEnum(PetStatus.SOLD.getStatusName());
		assertEquals(PetStatus.SOLD, returnPetStatus);

		// test case sensitive case
		returnPetStatus = PetStatus.getEnum(PetStatus.SOLD.getStatusName().toUpperCase());
		assertEquals(PetStatus.SOLD, returnPetStatus);
	}

	@Test
	public void testGetValues_invalidCase() {
		try {
			PetStatus returnPetStatus = PetStatus.getEnum("something invalid");
			fail("Shouldn't get here - expect an exception!");
		} catch (IllegalArgumentException iae) {
			// this is the expected good condition ...
		}
	}

}
