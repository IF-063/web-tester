package com.softserve.webtester.validator;

import com.sun.xml.internal.ws.server.ServerRtException;
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
 *
 * @author Anton Mykytiuk
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

    // TODO AM: add java doc
    @Override
    public void validate(Object object, Errors errors) {

        BuildVersion buildVersion = (BuildVersion) object;
        if (!metaDataService.isBuildVersionNameFree(buildVersion.getName(), buildVersion.getId())) {
            errors.rejectValue(NAME_FIELD, "NotUnique.buildVersion.name");
        }

    }
}
