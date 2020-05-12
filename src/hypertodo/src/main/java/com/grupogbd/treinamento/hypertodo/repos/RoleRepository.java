package com.grupogbd.treinamento.hypertodo.repos;

import com.grupogbd.treinamento.hypertodo.models.Role;
import org.springframework.data.repository.Repository;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
public interface RoleRepository extends Repository<Role, Integer> {

    Role findByName(String name);

}
