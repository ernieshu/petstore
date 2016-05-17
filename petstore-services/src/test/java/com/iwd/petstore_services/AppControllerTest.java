package com.iwd.petstore_services;

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

import com.iwd.petstore_services.domain.Pet;
import com.iwd.petstore_services.domain.PetPhotoURLs;

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
		Long invalidInput = (long) -5;
		ResponseEntity<String> testOutput = fixture.get(invalidInput);
		assertEquals(testOutput.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void getPet_NotFound() {
		Mockito.when(petStoreService.get(Mockito.anyLong())).thenReturn(null);
		ResponseEntity<String> testOutput = fixture.get((long) 5);
		assertEquals(testOutput.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	public void getPet_validCase() {
		Mockito.when(petStoreService.get(Mockito.anyLong())).thenReturn(new Pet());
		ResponseEntity<String> testOutput = fixture.get((long) 5);
		assertEquals(testOutput.getStatusCode(), HttpStatus.ACCEPTED);
		assertNotNull(testOutput.getBody());

	}

	@Test
	public void createPet_validCase() {
		Pet validPet = createValidPet();
		Mockito.when(petStoreService.add(Mockito.any(Pet.class))).thenReturn(validPet);
		ResponseEntity<String> testOutput = fixture.create(validPet);
		assertEquals(testOutput.getStatusCode(), HttpStatus.ACCEPTED);
		assertNotNull(testOutput.getBody());
	}

	private Pet createValidPet() {
		Pet validPet = new Pet();
		validPet.setId((long) 1);
		validPet.setName("petName");
		HashSet<PetPhotoURLs> photoUrls = new HashSet<PetPhotoURLs>();
		PetPhotoURLs petPhotoURL = new PetPhotoURLs();
		petPhotoURL.setId(1);
		petPhotoURL.setPhotoURL("string");
		photoUrls.add(petPhotoURL);
		validPet.setPhotoURLs(photoUrls);
		return validPet;
	}

	@Test
	public void createPet_invalidCase_noName() {
		Pet invalidPet = createValidPet();
		invalidPet.setName(null);
		Mockito.when(petStoreService.add(Mockito.any(Pet.class))).thenReturn(invalidPet);
		ResponseEntity<String> testOutput = fixture.create(invalidPet);
		assertEquals(testOutput.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void createPet_invalidCase_systemFailure() {
		Mockito.when(petStoreService.add(Mockito.any(Pet.class))).thenReturn(null);
		ResponseEntity<String> testOutput = fixture.create(createValidPet());
		assertEquals(testOutput.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	public void deletePet_validCase() {
		Pet validPet = createValidPet();
		Mockito.when(petStoreService.get(Mockito.anyLong())).thenReturn(validPet);
		Mockito.doNothing().when(petStoreService).delete(Mockito.any(Pet.class));
		ResponseEntity<String> testOutput = fixture.delete(validPet.getId());
		assertEquals(testOutput.getStatusCode(), HttpStatus.ACCEPTED);
	}

	@Test
	public void deletePet_invalidCase_notFound() {
		Mockito.when(petStoreService.get(Mockito.anyLong())).thenReturn(null);
		ResponseEntity<String> testOutput = fixture.delete((long) 1);
		assertEquals(testOutput.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	public void deletePet_invalidCase_invalidInput() {
		ResponseEntity<String> testOutput = fixture.delete((long) -1);
		assertEquals(testOutput.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
}
