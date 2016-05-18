package com.iwd.petstore.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.iwd.petstore.services.dao.domain.Pet;
import com.iwd.petstore.services.dao.domain.PetPhotoURL;
import com.iwd.petstore.services.domain.PetTo;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest {
	@InjectMocks
	AppController fixture = new AppController();

	@Mock
	private PetStoreService petStoreService;

	@Test
	public void getPet_InvalidInput() {
		Integer invalidInput = -5;
		ResponseEntity<Pet> testOutput = fixture.get(invalidInput);
		assertEquals(testOutput.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void getPet_NotFound() {
		Mockito.when(petStoreService.get(Mockito.anyInt())).thenReturn(null);
		ResponseEntity<Pet> testOutput = fixture.get(5);
		assertEquals(testOutput.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	public void getPet_validCase() {
		Mockito.when(petStoreService.get(Mockito.anyInt())).thenReturn(new Pet());
		ResponseEntity<Pet> testOutput = fixture.get(5);
		assertEquals(testOutput.getStatusCode(), HttpStatus.OK);
		assertNotNull(testOutput.getBody());

	}

	@Test
	public void createPet_validCase() {
		PetTo validPetTo = createValidPetTo();
		Pet validPet = createValidPet();
		Mockito.when(petStoreService.add(Mockito.any(Pet.class))).thenReturn(validPet);
		ResponseEntity<PetTo> testOutput = fixture.create(validPetTo);
		assertEquals(testOutput.getStatusCode(), HttpStatus.OK);
		assertNotNull(testOutput.getBody());
	}

	private Pet createValidPet() {
		Pet validPet = new Pet();
		validPet.setId(1);
		validPet.setName("petName");
		HashSet<PetPhotoURL> photoUrls = new HashSet<PetPhotoURL>();
		PetPhotoURL petPhotoURL = new PetPhotoURL();
		petPhotoURL.setPetId(1);
		petPhotoURL.setPhotoURL("string");
		photoUrls.add(petPhotoURL);
		validPet.setPhotoURLs(photoUrls);
		return validPet;
	}

	private PetTo createValidPetTo() {
		PetTo validPet = new PetTo();
		validPet.setId(1);
		validPet.setName("petName");
		List<String> photoUrls = new ArrayList<String>();
		photoUrls.add("String");
		validPet.setPhotoUrls(photoUrls);
		return validPet;
	}

	@Test
	public void createPet_invalidCase_noName() {
		PetTo invalidPet = createValidPetTo();
		invalidPet.setName(null);
		ResponseEntity<PetTo> testOutput = fixture.create(invalidPet);
		assertEquals(testOutput.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void deletePet_validCase() {
		Pet validPet = createValidPet();
		Mockito.when(petStoreService.get(Mockito.anyInt())).thenReturn(validPet);
		Mockito.doNothing().when(petStoreService).delete(Mockito.any(Pet.class));
		ResponseEntity<String> testOutput = fixture.delete(validPet.getId());
		assertEquals(testOutput.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void deletePet_invalidCase_notFound() {
		Mockito.when(petStoreService.get(Mockito.anyInt())).thenReturn(null);
		ResponseEntity<String> testOutput = fixture.delete(1);
		assertEquals(testOutput.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	public void deletePet_invalidCase_invalidInput() {
		ResponseEntity<String> testOutput = fixture.delete(-1);
		assertEquals(testOutput.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
}
