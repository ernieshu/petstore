package com.iwd.petstore.services.util;

import com.iwd.petstore.services.dao.domain.Category;
import com.iwd.petstore.services.dao.domain.Pet;
import com.iwd.petstore.services.dao.domain.Tag;
import com.iwd.petstore.services.domain.CategoryTo;
import com.iwd.petstore.services.domain.PetTo;
import com.iwd.petstore.services.domain.TagTo;

public interface ConversionUtils {

	public Pet convert(PetTo petTo);

	public Category convert(CategoryTo category);

	public CategoryTo convert(Category category);

	public Tag convert(TagTo tagTo);

	public TagTo convert(Tag tag);

	public PetTo convert(Pet pet);
	
}
