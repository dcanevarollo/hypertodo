package com.grupogbd.treinamento.hypertodo.services.user;

import com.grupogbd.treinamento.hypertodo.exceptions.WrongPasswordException;
import com.grupogbd.treinamento.hypertodo.models.User;
import com.grupogbd.treinamento.hypertodo.models.utils.NewPassword;
import com.grupogbd.treinamento.hypertodo.repos.UserRepository;
import com.grupogbd.treinamento.hypertodo.utils.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Douglas Canevarollo
 * @date : 26/04/2020
 */
@Service
public class UpdatePassword {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public void execute(NewPassword newPassword) {
        User user = LoggedUser.getUser();

        if (!encoder.matches(newPassword.getCurrentPassword(), user.getPassword()))
            throw new WrongPasswordException();

        user.setPassword(encoder.encode(newPassword.getNewPassword()));
        repository.save(user);
    }

}
