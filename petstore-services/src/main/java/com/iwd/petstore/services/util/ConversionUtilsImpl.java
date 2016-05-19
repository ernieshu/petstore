package com.iwd.petstore.services.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.iwd.petstore.services.dao.domain.Category;
import com.iwd.petstore.services.dao.domain.Pet;
import com.iwd.petstore.services.dao.domain.PetPhotoURL;
import com.iwd.petstore.services.dao.domain.Tag;
import com.iwd.petstore.services.domain.CategoryTo;
import com.iwd.petstore.services.domain.PetTo;
import com.iwd.petstore.services.domain.TagTo;

@Component("conversionUtils")
public class ConversionUtilsImpl implements ConversionUtils {

	public Pet convert(PetTo petTo) {
		Pet returnPet = new Pet();
		if (petTo.getCategory() != null) {
			returnPet.setCategory(convert(petTo.getCategory()));
		}
		returnPet.setId(petTo.getId());
		returnPet.setName(petTo.getName());
		if (petTo.getPhotoUrls() != null && petTo.getPhotoUrls().size() > 0) {
			List<PetPhotoURL> petPhotoUrls = new ArrayList<PetPhotoURL>();
			for (String photoUrlString : petTo.getPhotoUrls()) {
				PetPhotoURL ppu = new PetPhotoURL();
				ppu.setPhotoURL(photoUrlString);
				petPhotoUrls.add(ppu);
			}
			returnPet.setPhotoURLs(petPhotoUrls);
		}
		if (petTo.getStatus() != null) {
			returnPet.setStatus(petTo.getStatus());
		}
		if (petTo.getTags() != null && petTo.getTags().size() > 0) {

			Set<Tag> tags = new HashSet<Tag>();
			for (TagTo tagTo : petTo.getTags()) {
				tags.add(convert(tagTo));
			}
			returnPet.setTags(tags);
		}

		return returnPet;
	}

	public Category convert(CategoryTo category) {
		Category returnCategory = new Category();
		returnCategory.setId(category.getId());
		returnCategory.setName(category.getName());
		return returnCategory;
	}

	public CategoryTo convert(Category category) {
		CategoryTo returnCategory = new CategoryTo();
		returnCategory.setId(category.getId());
		returnCategory.setName(category.getName());
		return returnCategory;
	}

	public Tag convert(TagTo tagTo) {
		Tag returnTag = new Tag();
		returnTag.setId(tagTo.getId());
		returnTag.setName(tagTo.getName());
		return returnTag;
	}

	public TagTo convert(Tag tag) {
		TagTo returnTag = new TagTo();
		returnTag.setId(tag.getId());
		returnTag.setName(tag.getName());
		return returnTag;
	}

	public PetTo convert(Pet pet) {
		PetTo returnPet = new PetTo();
		if (pet.getCategory() != null) {
			returnPet.setCategory(convert(pet.getCategory()));
		}
		returnPet.setId(pet.getId());
		returnPet.setName(pet.getName());

		if (pet.getPhotoURLs() != null && !pet.getPhotoURLs().isEmpty()) {
			List<String> photoUrls = new ArrayList<String>();
			for (PetPhotoURL ppu : pet.getPhotoURLs()) {
				String photoUrl = ppu.getPhotoURL();
				photoUrls.add(photoUrl);
			}

			returnPet.setPhotoUrls(photoUrls);
		}
		if (pet.getStatus() != null) {
			returnPet.setStatus(pet.getStatus());
		}
		if (pet.getTags() != null && !pet.getTags().isEmpty()) {
			List<TagTo> tags = new ArrayList<TagTo>();
			for (Tag tag : pet.getTags()) {
				tags.add(convert(tag));
			}
			returnPet.setTags(tags);
		}

		return returnPet;
	}
	
}
