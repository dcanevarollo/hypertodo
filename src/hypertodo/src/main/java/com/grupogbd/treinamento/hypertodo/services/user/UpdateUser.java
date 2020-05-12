package com.grupogbd.treinamento.hypertodo.services.user;

import com.grupogbd.treinamento.hypertodo.exceptions.WrongPasswordException;
import com.grupogbd.treinamento.hypertodo.models.User;
import com.grupogbd.treinamento.hypertodo.repos.UserRepository;
import com.grupogbd.treinamento.hypertodo.utils.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Douglas Canevarollo
 * @date : 26/04/2020
 */
@Service
public class UpdateUser {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public void execute(User updatedUser) {
        User user = LoggedUser.getUser();

        if (!encoder.matches(updatedUser.getPassword(), user.getPassword()))
            throw new WrongPasswordException();

        user.merge(updatedUser);

        // Atualiza as informações do usuário autenticado na sessão
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getRoles());
        SecurityContextHolder.getContext().setAuthentication(auth);

        repository.save(user);
    }

}
