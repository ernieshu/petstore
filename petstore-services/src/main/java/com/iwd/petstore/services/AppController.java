package com.iwd.petstore.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwd.petstore.services.dao.domain.Category;
import com.iwd.petstore.services.dao.domain.Pet;
import com.iwd.petstore.services.dao.domain.PetPhotoURL;
import com.iwd.petstore.services.dao.domain.Tag;
import com.iwd.petstore.services.domain.CategoryTo;
import com.iwd.petstore.services.domain.PetTo;
import com.iwd.petstore.services.domain.TagTo;

@Controller
@SpringBootApplication
public class AppController {

	@Autowired
	PetStoreService petStoreService;

	@RequestMapping(value = "/pet/{petId}", method = { RequestMethod.GET })
	@ResponseBody
	@Transactional(readOnly = true)
	ResponseEntity<Pet> get(@PathVariable Integer petId) {
		if (petId < 0) {
			return new ResponseEntity<Pet>(HttpStatus.BAD_REQUEST);
		}

		Pet returnPet = petStoreService.get(petId);
		if (returnPet == null) {
			return new ResponseEntity<Pet>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Pet>(returnPet, HttpStatus.OK);
	}

	@RequestMapping(value = "/pet", method = { RequestMethod.POST })
	ResponseEntity<PetTo> create(@RequestBody PetTo petTo) {

		if (!validatePet(petTo)) {
			return new ResponseEntity<PetTo>(HttpStatus.BAD_REQUEST);
		} else {

			Pet petToCommit = convert(petTo);

			petToCommit = petStoreService.add(petToCommit);

			PetTo petToReturn = convert(petToCommit);

			return new ResponseEntity<PetTo>(petToReturn, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/pet/{petId}", method = { RequestMethod.DELETE })
	@ResponseBody
	ResponseEntity<String> delete(@PathVariable Integer petId) {
		if (petId < 0) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		Pet returnPet = petStoreService.get(petId);
		if (returnPet == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		petStoreService.delete(returnPet);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@Bean
	public ServletRegistrationBean h2ServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppController.class, args);
	}

	private boolean validatePet(PetTo pet) {
		if (pet.getName() == null || pet.getName().length() == 0) {
			return false;
		}
		if (pet.getPhotoUrls() == null || pet.getPhotoUrls().size() == 0) {
			return false;
		}
		return true;
	}

	protected Pet convert(PetTo petTo) {
		Pet returnPet = new Pet();
		if (petTo.getCategory() != null) {
			returnPet.setCategory(convert(petTo.getCategory()));
		}
		returnPet.setId(petTo.getId());
		returnPet.setName(petTo.getName());
		if (petTo.getPhotoUrls() != null && petTo.getPhotoUrls().size() > 0) {
			Set<PetPhotoURL> petPhotoUrls = new HashSet<PetPhotoURL>();
			for (String photoUrlString : petTo.getPhotoUrls()) {
				PetPhotoURL ppu = new PetPhotoURL();
				ppu.setPetId(petTo.getId());
				ppu.setPhotoURL(photoUrlString);
				petPhotoUrls.add(ppu);
			}
			returnPet.setPhotoURLs(petPhotoUrls);
		}
		if (petTo.getPetStatus() != null) {
			returnPet.setStatus(petTo.getPetStatus());
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

	protected Category convert(CategoryTo category) {
		Category returnCategory = new Category();
		returnCategory.setId(category.getId());
		returnCategory.setName(category.getName());
		return returnCategory;
	}

	protected CategoryTo convert(Category category) {
		CategoryTo returnCategory = new CategoryTo();
		returnCategory.setId(category.getId());
		returnCategory.setName(category.getName());
		return returnCategory;
	}

	protected Tag convert(TagTo tagTo) {
		Tag returnTag = new Tag();
		returnTag.setId(tagTo.getId());
		returnTag.setName(tagTo.getName());
		return returnTag;
	}

	protected TagTo convert(Tag tag) {
		TagTo returnTag = new TagTo();
		returnTag.setId(tag.getId());
		returnTag.setName(tag.getName());
		return returnTag;
	}

	protected PetTo convert(Pet pet) {
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
			returnPet.setPetStatus(pet.getStatus());
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
