package it.uniroma3.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.model.Chef;
import it.uniroma3.catering.service.ChefService;

@Component
public class ChefValidator implements Validator {

	@Autowired
	private ChefService cs;

	@Override
	public boolean supports(Class<?> clazz) {
		return Chef.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Chef chef = (Chef) target;
		if(this.cs.existsByFirstNameAndLastName(chef.getFirstName(), chef.getLastName())) {
			errors.rejectValue("firstName", "");
			errors.rejectValue("lastName", "");
		}
	} 
	
}