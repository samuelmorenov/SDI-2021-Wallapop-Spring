package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Offer;

@Component
public class OfferValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Offer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");

		if(offer.getTitle().length() < 5) {
			errors.rejectValue("title", "Error.offer.title");
		}
		if(offer.getDescription().length() < 20) {
			errors.rejectValue("description", "Error.offer.description");
		}		
		if (offer.getPrice() == null || offer.getPrice()<= 1) {
			errors.rejectValue("price", "Error.offer.price");
		}
	}
}
