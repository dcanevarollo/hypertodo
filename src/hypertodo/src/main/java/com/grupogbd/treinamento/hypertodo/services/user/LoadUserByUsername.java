package com.grupogbd.treinamento.hypertodo.services.user;

import com.grupogbd.treinamento.hypertodo.models.User;
import com.grupogbd.treinamento.hypertodo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : Douglas Canevarollo
 * @date : 26/04/2020
 */
// LoadUserByUsername implementa os métodos da interface UserDetailsService do Spring Security. Assim, é obrigatório que
// o método loadUserByUsername() seja sobrescrito para que o login seja efetuado
@Service
public class LoadUserByUsername implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    // Método de login
    // Basta encontrarmos o usuário pelo e-mail digitado (o username definido no input de login)
    // O Spring Security fará o resto para nós
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);

        if (user == null) throw new UsernameNotFoundException("Usuário não encontrado");

        return user;
    }

}
