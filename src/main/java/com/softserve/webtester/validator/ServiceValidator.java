package com.softserve.webtester.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softserve.webtester.model.Service;
import com.softserve.webtester.service.MetaDataService;

@Component
public class ServiceValidator implements Validator {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Service.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Service service = (Service) target;
        if (!metaDataService.isServiceNameFree(service.getName(), service.getId())) {
            errors.rejectValue("name", null, "name should be unique");
        }

    }

}
