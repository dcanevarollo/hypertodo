package com.grupogbd.treinamento.hypertodo.repos;

import com.grupogbd.treinamento.hypertodo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
// CrudRepository já implementa métodos para CRUDs, como save(), delete(), findAll(), etc. Como parâmetros para a classe
// mãe, devemos passar qual model estamos tratando e a tipagem de seu id.
// Repositórios são interfaces de métodos "já implementados"
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    boolean existsByEmail(String email);
    
}
