package it.uniroma3.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.service.IngredientService;

@Component
public class IngredientValidator implements Validator {

	@Autowired
	private IngredientService is;

	@Override
	public boolean supports(Class<?> clazz) {
		return IngredientValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) { }

}
