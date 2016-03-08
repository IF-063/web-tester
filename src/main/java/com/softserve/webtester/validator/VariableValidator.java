package com.softserve.webtester.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softserve.webtester.model.Variable;

/**
 * Implementation of {@link Validator} interface for checking {@link Variable} instance's <code>length</code> property.
 * 
 * @author Taras Oglabyak
 *
 */
@Component
public class VariableValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Variable.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Variable variable = (Variable) target;
        if (variable.isRandom() && (variable.getLength() == null || variable.getLength() < 1)) {
            errors.rejectValue("length", null, "length is not valid");
        }
    }
}