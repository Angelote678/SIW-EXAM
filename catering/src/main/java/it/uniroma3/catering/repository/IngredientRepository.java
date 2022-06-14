package it.uniroma3.catering.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.catering.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

	public boolean existsByName(String name);

	public Ingredient findByName(String name);

}
