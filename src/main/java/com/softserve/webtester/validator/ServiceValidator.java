package com.softserve.webtester.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotBlank.service.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotBlank.service.description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sla", "NotBlank.service.sla");
        Service service = (Service) target;
        if (!metaDataService.isServiceNameFree(service.getName(), service.getId())) {
            errors.rejectValue("name", null, "Name should be unique");
        }

    }

}
