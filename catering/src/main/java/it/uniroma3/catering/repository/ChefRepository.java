package it.uniroma3.catering.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.catering.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long> {

	public boolean existsByFirstNameAndLastName(String firstName, String lastName);

	public Chef findByFirstNameAndLastName(String firstName, String lastName);
	
}
