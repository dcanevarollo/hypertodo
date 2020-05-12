package com.grupogbd.treinamento.hypertodo.validators;

import com.grupogbd.treinamento.hypertodo.models.Task;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author : Douglas Canevarollo
 * @date : 25/04/2020
 */
public class TaskValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Task.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required");
    }

}
