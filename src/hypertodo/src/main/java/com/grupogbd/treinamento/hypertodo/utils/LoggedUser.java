package com.grupogbd.treinamento.hypertodo.utils;

import com.grupogbd.treinamento.hypertodo.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
@Component
public class LoggedUser {

    /**
     * Maneira fácil de recuperar as informações do usuário logado na sessão, sem a necessidade de instanciar objetos.
     *
     * @return {@link User} logado.
     */
    public static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
