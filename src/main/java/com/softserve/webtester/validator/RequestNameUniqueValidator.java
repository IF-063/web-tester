package com.softserve.webtester.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softserve.webtester.model.Request;
import com.softserve.webtester.service.RequestService;

/**
 * Implementation of {@link Validator} interface for checking the unique of {@link Request} instance's 
 * <code>name</code> property.
 * 
 * @author Taras Oglabyak
 *
 */
@Component
public class RequestNameUniqueValidator implements Validator {

    @Autowired
    private RequestService requestService;

    @Override
    public boolean supports(Class<?> clazz) {
	return Request.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	Request request = (Request) target;
	if (!requestService.isRequestNameFree(request.getName())) {
	    errors.rejectValue("name", null, "name should be unique");
	}
    }
}