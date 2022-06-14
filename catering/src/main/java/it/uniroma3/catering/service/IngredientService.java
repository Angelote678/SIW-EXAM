package it.uniroma3.catering.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.catering.model.Ingredient;
import it.uniroma3.catering.repository.IngredientRepository;

@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository ir;
	
	@Transactional
	public void save(Ingredient ingredient) {
		ir.save(ingredient);
	}

	public Ingredient findById(Long id) {
		return this.ir.findById(id).get();
	}

	@Transactional
	public void deleteById(Long id) {
		this.ir.deleteById(id);
	}

	public boolean existsByName(String name) {
		return this.ir.existsByName(name);
	}

	public @Valid Ingredient findByName(String name) {
		return this.ir.findByName(name);
	}
}
