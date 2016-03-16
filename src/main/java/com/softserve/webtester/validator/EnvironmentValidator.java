package com.softserve.webtester.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softserve.webtester.model.Environment;
import com.softserve.webtester.service.EnvironmentService;

@Component
public class EnvironmentValidator implements Validator {

    @Autowired
    private EnvironmentService environmentService;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Environment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        Environment environment = (Environment) target;
        if (environmentService.isNameFree(environment) > 0) {
            e.rejectValue("name", "NotUnique.environment.name", "Name should be unique");
        }

    }

}
