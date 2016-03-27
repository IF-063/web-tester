package com.softserve.webtester.validator;

import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.model.Service;
import com.softserve.webtester.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class GraphicValidator implements Validator {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ReportFilterDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serviceId", "NotBlank.reportFilterDTO.serviceId");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buildVersionId", "NotBlank.reportFilterDTO.buildVersionId");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "responseTimeFilterMarker", "NotBlank.service.reportFilterDTO");
        /*ReportFilterDTO reportFilterDTO = (ReportFilterDTO) target;
        if (!metaDataService.isServiceNameFree(service.getName(), service.getId())) {
            errors.rejectValue("name", null, "Name should be unique");
        }*/
    }
}