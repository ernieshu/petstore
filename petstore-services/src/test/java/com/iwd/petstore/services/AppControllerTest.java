package com.iwd.petstore.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.iwd.petstore.services.AppController;
import com.iwd.petstore.services.PetStoreService;
import com.iwd.petstore.services.domain.Pet;
import com.iwd.petstore.services.domain.PetPhotoURL;
import com.iwd.petstore.services.domain.PetURLCompositeKey;

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
		Pet validPet = createValidPet();
		Mockito.when(petStoreService.add(Mockito.any(Pet.class))).thenReturn(validPet);
		ResponseEntity<Pet> testOutput = fixture.create(validPet);
		assertEquals(testOutput.getStatusCode(), HttpStatus.OK);
		assertNotNull(testOutput.getBody());
	}

	private Pet createValidPet() {
		Pet validPet = new Pet();
		validPet.setId(1);
		validPet.setName("petName");
		HashSet<PetPhotoURL> photoUrls = new HashSet<PetPhotoURL>();
		PetPhotoURL petPhotoURL = new PetPhotoURL();
		PetURLCompositeKey petURLCompositeKey = new PetURLCompositeKey();
		petURLCompositeKey.setPetId(1);
		petURLCompositeKey.setPhotoURL("string");
		petPhotoURL.setPetURLCompositeKey(petURLCompositeKey);
		photoUrls.add(petPhotoURL);
		// validPet.setPhotoURLs(photoUrls);
		return validPet;
	}

	@Test
	public void createPet_invalidCase_noName() {
		Pet invalidPet = createValidPet();
		invalidPet.setName(null);
		Mockito.when(petStoreService.add(Mockito.any(Pet.class))).thenReturn(invalidPet);
		ResponseEntity<Pet> testOutput = fixture.create(invalidPet);
		assertEquals(testOutput.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void createPet_invalidCase_systemFailure() {
		Mockito.when(petStoreService.add(Mockito.any(Pet.class))).thenReturn(null);
		ResponseEntity<Pet> testOutput = fixture.create(createValidPet());
		assertEquals(testOutput.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
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
