package com.iwd.petstore.services.util;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

import com.iwd.petstore.services.dao.domain.Category;
import com.iwd.petstore.services.dao.domain.Pet;
import com.iwd.petstore.services.dao.domain.PetStatus;
import com.iwd.petstore.services.dao.domain.Tag;
import com.iwd.petstore.services.domain.CategoryTo;
import com.iwd.petstore.services.domain.PetTo;
import com.iwd.petstore.services.domain.TagTo;

public class ConversionUtilsTest {

	ConversionUtils fixture = new ConversionUtilsImpl();
	
	// PetTo valid test values
	public static final Integer VALID_PET_ID = 1;
	public static final String VALID_PET_NAME = "PetName";
	public static final Integer VALID_CATEGORY_ID = 10;
	public static final String VALID_CATEGORY_NAME = "Category 0";
	public static final String VALID_PHOTO_URL_ENTRY = "photoUrls";
	public static final PetStatus VALID_PET_STATUS = PetStatus.SOLD;
	public static final Integer VALID_TAG_ID = 100;
	public static final String VALID_TAG_NAME = "Tag 0";
	
	@Test
	public void testConvertingPet() {

		PetTo validPetTo = createValidPetTo();
		
		Pet petToCheck = fixture.convert(validPetTo);

		assertNotNull(petToCheck);
		assertEquals(VALID_PET_ID, petToCheck.getId());
		assertEquals(VALID_PET_NAME, petToCheck.getName());
		assertNotNull(petToCheck.getCategory());
		assertEquals(VALID_CATEGORY_ID, petToCheck.getCategory().getId());
		assertEquals(VALID_CATEGORY_NAME, petToCheck.getCategory().getName());
		assertNotNull(petToCheck.getPhotoURLs());
		assertEquals(1, petToCheck.getPhotoURLs().size());
		assertEquals(VALID_PHOTO_URL_ENTRY, petToCheck.getPhotoURLs().get(0).getPhotoURL());
		assertNotNull(petToCheck.getTags());
		assertEquals(1, petToCheck.getTags().size());
		Tag tagToCheck = (Tag) petToCheck.getTags().toArray()[0];
		assertEquals(VALID_TAG_ID, tagToCheck.getId());
		assertEquals(VALID_TAG_NAME, tagToCheck.getName());
	}

	
	private PetTo createValidPetTo() {
		PetTo petTo = new PetTo();
		CategoryTo validCategory = new CategoryTo();
		validCategory.setId(VALID_CATEGORY_ID);
		validCategory.setName(VALID_CATEGORY_NAME);
		
		petTo.setCategory(validCategory);
		petTo.setId(VALID_PET_ID);
		petTo.setName(VALID_PET_NAME);
		petTo.setPhotoUrls(Collections.singletonList(VALID_PHOTO_URL_ENTRY));
		petTo.setStatus(VALID_PET_STATUS);
		
		TagTo tagTo = new TagTo();
		tagTo.setId(VALID_TAG_ID);
		tagTo.setName(VALID_TAG_NAME);
		
		petTo.setTags(Collections.singletonList(tagTo));
		return petTo;		
	}
	
}
