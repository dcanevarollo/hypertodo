package com.grupogbd.treinamento.hypertodo.validators;

import com.grupogbd.treinamento.hypertodo.models.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
// Valida "automaticamente" o formulário de cadastro de usuários. As mensagens de retorno estão definidas no arquivo
// message.properties.
// Como UserValidator 'implementa' Validator, os dois métodos sobrescritos são necessários. Na prática, apenas o método
// validate() será editado por nós, que é onde a lógica de validação realmente acontece
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // Verifica se algum campo obrigatório não foi preenchido
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");

        // Para validações mais específicas, precisamos fazer um cast do objeto genérico recebido
        // transformando-o em um objeto da classe User
        User user = (User) o;

        // Se todos os campos foram preenchidos, verifica se a senha possui pelo menos 6 caracteres
        if (!errors.hasFieldErrors("password") && user.getPassword().length() < 6)
            errors.rejectValue("password", "field.size");
    }

}
