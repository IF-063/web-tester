package com.softserve.webtester.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softserve.webtester.model.Environment;
import com.softserve.webtester.service.EnvironmentService;

@Component
public class EnvironmentValidator implements Validator {
    
    private static final String DB_PORT_REGEX_PATTERN = "\\d{1,5}";

    @Autowired
    private EnvironmentService environmentService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Environment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        Environment environment = (Environment) target;
        if (environmentService.isNameFree(environment) > 0) {
            e.rejectValue("name", "NotUnique.environment.name", "Name should be unique");
        }
        
        if (!isMatched(Integer.toString(environment.getDbPort()))) {
            e.rejectValue("dbPort", "Pattern.environment.dbPort", "Database port is not valid");
        }

    }

    public boolean isMatched(String dbPort) {
        Pattern pattern = Pattern.compile(DB_PORT_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(dbPort);
        return matcher.matches();
    }

}
