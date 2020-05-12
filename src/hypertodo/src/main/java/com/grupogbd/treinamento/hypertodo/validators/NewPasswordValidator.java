package com.grupogbd.treinamento.hypertodo.validators;

import com.grupogbd.treinamento.hypertodo.models.utils.NewPassword;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author : Douglas Canevarollo
 * @date : 02/04/2020
 */
@Component
public class NewPasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return NewPassword.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "field.required");

        NewPassword newPassword = (NewPassword) o;

        if (!errors.hasFieldErrors("newPassword") && newPassword.getNewPassword().length() < 6)
            errors.rejectValue("newPassword", "field.size");
    }

}
