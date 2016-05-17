package com.iwd.petstore_services;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.staticmock.MockStaticEntityMethods;

import com.iwd.petstore_services.domain.Pet;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest
{
	@InjectMocks
	AppController fixture = new AppController();
	
	@Mock
	private PetStoreService petStoreService;
		
    @Test
    public void getPet_InvalidInput() {
    	Long invalidInput = (long) -5;
    	ResponseEntity<String> testOutput = fixture.get(invalidInput);
    	assertEquals(testOutput.getStatusCode(),HttpStatus.BAD_REQUEST);
    }
    
    @Test
    public void getPet_NotFound() {
    	Mockito.when(petStoreService.get(Mockito.anyLong())).thenReturn(null);
    	ResponseEntity<String> testOutput = fixture.get((long) 5);
    	assertEquals(testOutput.getStatusCode(),HttpStatus.NOT_FOUND);
    }
    
    @Test
    public void getPet_validCase() {
    	Mockito.when(petStoreService.get(Mockito.anyLong())).thenReturn(new Pet());
    	ResponseEntity<String> testOutput = fixture.get((long) 5);
    	assertEquals(testOutput.getStatusCode(),HttpStatus.ACCEPTED);
    	assertNotNull(testOutput.getBody());
        	
    }
    
    @Test
    public void createPet_validCase() {
    	Pet validPet = new Pet();
    	validPet.setId((long) 1);
    	validPet.setName("petName");
    	Set<String> photoUrls = new HashSet<String>();
    	photoUrls.add("string");
    	validPet.setPhotoURLs(photoUrls);
    	Mockito.when(petStoreService.add(Mockito.any(Pet.class))).thenReturn(validPet);
    	ResponseEntity<String> testOutput = fixture.create(validPet);
    	assertEquals(testOutput.getStatusCode(),HttpStatus.ACCEPTED);
    	assertNotNull(testOutput.getBody());
    }

    @Test
    public void createPet_invalidCase_noName() {
    	Pet validPet = new Pet();
    	validPet.setId((long) 1);
    	Mockito.when(petStoreService.add(Mockito.any(Pet.class))).thenReturn(validPet);
    	ResponseEntity<String> testOutput = fixture.create(validPet);
    	assertEquals(testOutput.getStatusCode(),HttpStatus.BAD_REQUEST);
    }    
    
}
