package com.softserve.webtester.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softserve.webtester.model.Application;
import com.softserve.webtester.service.MetaDataService;

@Component
public class ApplicationValidator implements Validator {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Application.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Application application = (Application) target;
        if (!metaDataService.isApplicationNameFree(application.getName(), application.getId())) {
            errors.rejectValue("name", null, "name should be unique");
        }

    }

}
