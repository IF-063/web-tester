package com.softserve.webtester.validator;

import com.softserve.webtester.model.Label;
import com.softserve.webtester.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

/**
 * Implementation of {@link Validator} interface for additional checking {@link Label} instance. Checks the
 * unique of label's parameter name and if name field is empty
 */
@Component
public class LabelValidator implements Validator {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public boolean supports(Class clazz) {
        return Label.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotBlank.label.name");
        Label label = (Label) object;
        if (!metaDataService.isLabelNameFree(label.getName(),
                label.getId())) {
            errors.rejectValue("name", "NotUnique.label.name");
        }
    }
}
