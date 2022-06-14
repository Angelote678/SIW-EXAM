package it.uniroma3.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.model.Dish;
import it.uniroma3.catering.service.DishService;

@Component
public class DishValidator implements Validator {
	
	@Autowired
	private DishService ds;

	@Override
	public boolean supports(Class<?> clazz) {
		return Dish.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Dish dish = (Dish) target;
		if(this.ds.existsByName(dish.getName())) {
			errors.rejectValue("name", "nomepiatto.duplicato");
		}
	}

}
