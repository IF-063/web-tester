package com.softserve.webtester.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.webtester.model.Label;
import com.softserve.webtester.service.MetaDataService;

/**
 * Implementation of {@link Validator} interface for additional checking {@link Label} instance. Checks the
 * unique of label's parameter name and if name field is empty
 */
@Component
public class LabelValidator implements Validator {

    private static final String NAME_FIELD = "name";

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public boolean supports(Class clazz) {
        return Label.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, NAME_FIELD, "NotBlank.label.name");
        Label label = (Label) object;
        if (!metaDataService.isLabelNameFree(label.getName(),
                label.getId())) {
            errors.rejectValue(NAME_FIELD, "NotUnique.label.name");
        }
    }
}
