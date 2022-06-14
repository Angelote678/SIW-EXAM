package it.uniroma3.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.service.BuffetService;

@Component
public class BuffetValidator implements Validator {
	
	@Autowired
	private BuffetService bs;

	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Buffet buffet = (Buffet) target;
		if (this.bs.existsByName(buffet.getName())) {
			errors.rejectValue("name", "nomebuffet.duplicato");
		}
	}
	
	
}
