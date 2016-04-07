package com.softserve.webtester.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.service.MetaDataService;

/**
 * Implementation of {@link Validator} interface for additional checking
 * {@link BuildVersion} instance. Checks the unique of build version's parameter
 * name and if name, description fields are empty
 */
@Component
public class BuildVersionValidator implements Validator {

    private static final String NAME_FIELD = "name";

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public boolean supports(Class clazz) {
        return BuildVersion.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, NAME_FIELD, "NotBlank.buildVersion.name"); // TODO AM: please check /web-tester/src/main/java/com/softserve/webtester/model/Environment.java and do in the same way
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotBlank.buildVersion.description"); // no need to have here validation, You can add annotations on object. If sth, pleas ping Victor Z
        BuildVersion buildVersion = (BuildVersion) object;
        if (!metaDataService.isBuildVersionNameFree(buildVersion.getName(), buildVersion.getId())) {
            errors.rejectValue(NAME_FIELD, "NotUnique.buildVersion.name");
        }

    }
}
