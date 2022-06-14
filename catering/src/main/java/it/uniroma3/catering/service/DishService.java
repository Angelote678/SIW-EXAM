package it.uniroma3.catering.service;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.catering.model.Dish;
import it.uniroma3.catering.repository.DishRepository;

@Service
public class DishService {

	@Autowired
	private DishRepository dr;
	
	@Transactional
	public void save(Dish dish) {
		dr.save(dish);
	}

	public Dish findById(Long id) {
		return this.dr.findById(id).get();
	}

	public boolean existsByName(String name) {
		return this.dr.existsByName(name);
	}

	@Transactional
	public void deleteById(Long id) {
		this.dr.deleteById(id);
	}
	
}
