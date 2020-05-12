package com.grupogbd.treinamento.hypertodo.services.role;

import com.grupogbd.treinamento.hypertodo.models.Role;
import com.grupogbd.treinamento.hypertodo.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
@Service
public class FindRoleByName {

    @Autowired
    private RoleRepository repository;

    public Role execute(String name) {
        return repository.findByName(name);
    }

}
