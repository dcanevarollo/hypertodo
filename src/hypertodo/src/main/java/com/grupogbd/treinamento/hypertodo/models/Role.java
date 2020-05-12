package com.grupogbd.treinamento.hypertodo.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
@Entity
@Data
// A classe Role é filha da interface mãe GrantedAuthority, do Spring Security
// Assim, o Spring sabe que esse model mantém as permissões de usuários
// Como GrantedAuthority é uma interface, Role a 'implementa' em vez de 'estendê-la'
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // O método sobrescrito getAuthority() é necessário para toda subclasse de GrantedAuthority
    @Override
    public String getAuthority() {
        return this.name;
    }

}
