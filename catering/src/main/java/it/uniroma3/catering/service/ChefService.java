package it.uniroma3.catering.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.catering.model.Chef;
import it.uniroma3.catering.repository.ChefRepository;

@Service
public class ChefService {
	
	@Autowired
	private ChefRepository cr;
	
	@Transactional
	public void save(Chef chef) {
		cr.save(chef);
	}

	public Chef findById(Long id) {
		return this.cr.findById(id).get();
	}

	public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
		return this.cr.existsByFirstNameAndLastName(firstName, lastName);
	}

	public @Valid Chef findByFirstNameAndLastName(String firstName, String lastName) {
		return this.cr.findByFirstNameAndLastName(firstName, lastName);
	}
}
