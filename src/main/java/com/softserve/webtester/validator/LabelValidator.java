package com.softserve.webtester.validator;

import com.softserve.webtester.model.Label;
import com.softserve.webtester.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

@Component
public class LabelValidator implements Validator {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Label.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Label label = (Label) target;
        if (!metaDataService.isLabelNameFree(label.getName(),
                label.getId())) {
            errors.rejectValue("name", "NotUnique.label.name", "Name should be unique");
        }

    }
}
