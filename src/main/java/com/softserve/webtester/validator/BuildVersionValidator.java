package com.softserve.webtester.validator;

import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

/**
 * Implementation of {@link Validator} interface for additional checking {@link BuildVersion} instance. Checks the
 * unique of build version's parameter name and if name, description fields are empty
 */
@Component
public class BuildVersionValidator implements Validator {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public boolean supports(Class clazz) {
        return BuildVersion.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotBlank.buildVersion.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotBlank.buildVersion.description");
        BuildVersion buildVersion = (BuildVersion) object;
        if (!metaDataService.isBuildVersionNameFree(buildVersion.getName(),
                buildVersion.getId())) {
            errors.rejectValue("name", "NotUnique.buildVersion.name");
        }

    }
}
