package com.softserve.webtester.validator;

import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

@Component
public class BuildVersionValidator implements Validator {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public boolean supports(Class<?> clazz) {
        return BuildVersion.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BuildVersion buildVersion = (BuildVersion) target;
        if (!metaDataService.isBuildVersionNameFree(buildVersion.getName(),
                buildVersion.getId())) {
            errors.rejectValue("name", "NotUnique.buildVersion.name", "Name should be unique");
        }

    }
}
