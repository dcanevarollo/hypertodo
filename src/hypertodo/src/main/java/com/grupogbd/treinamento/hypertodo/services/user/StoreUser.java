package com.grupogbd.treinamento.hypertodo.services.user;

import com.grupogbd.treinamento.hypertodo.exceptions.UserAlreadyExistsException;
import com.grupogbd.treinamento.hypertodo.models.Role;
import com.grupogbd.treinamento.hypertodo.models.User;
import com.grupogbd.treinamento.hypertodo.repos.UserRepository;
import com.grupogbd.treinamento.hypertodo.services.role.FindRoleByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Douglas Canevarollo
 * @date : 26/04/2020
 */
@Service
public class StoreUser {

    @Autowired
    private UserRepository repository;

    // FindRoleByName é um service que busca a permissão do usuário armazenada no banco de dados
    // Ele está localizado dentro de services/role/
    @Autowired
    private FindRoleByName findRoleByName;

    // Ferramenta de criptografia de senhas definido no arquivo HypertodoApplication
    @Autowired
    private PasswordEncoder encoder;
    
    public void execute(User user) {
        boolean emailAlreadyRegistered = repository.existsByEmail(user.getEmail());

        // Lança uma exceção caso o e-mail já tenha sido cadastrado
        if (emailAlreadyRegistered)
            throw new UserAlreadyExistsException();

        user.setPassword(encoder.encode(user.getPassword())); // Criptografa a senha antes de salvar

        // Adicionar uma permissão à lista de permissões do usuário automaticamente preenche a tabela pivô role_user
        // Por padrão, todas as roles devem ser acompanhadas do prefixo 'ROLE_'
        Role role = findRoleByName.execute("ROLE_USER");
        user.getRoles().add(role);

        repository.save(user);
    }

}
