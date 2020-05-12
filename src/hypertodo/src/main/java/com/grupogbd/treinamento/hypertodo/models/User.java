package com.grupogbd.treinamento.hypertodo.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
@Entity
// Como 'user' é uma palavra reservada do PostgreSQL, devemos informar ao Spring que é essa entidade pertence a uma
// tabela user do schema public para que o mapeamento funcione
@Table(name = "user", schema = "public")
@Data
// O model User implementa a interface UserDetails do Spring Security. Esta deve, obrigatoriamente, conter os métodos
// sobrescritos criados (getAuthorities(), getUsername(), isAccountNonExpired(), etc.).
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // 'user', em mappedBy, refere-se ao atributo referente ao usuário no model Task
    // Ou seja, o model Task possui um atributo 'user', anotado com @ManyToOne
    // Esse é o outro lado da relação
    @OneToMany(mappedBy = "user")
    private Set<Task> tasks = new HashSet<>();  // Um usuário possui um conjunto de tarefas

    // Sempre buscaremos os dados de acesso do usuário quando buscarmos por ele, assim, definimos o tipo de busca como
    // EAGER. Em outras palavras, sempre que buscarmos um usuário, suas permissões também serão carregas na memória
    @ManyToMany(fetch = FetchType.EAGER)
    // A tabela pivô, no Spring Data, é chamada de Join Table. Aqui, passamos as informações referentes a essa relação
    @JoinTable(
            name = "role_user", // Nome da tabela pivô (de relação)
            joinColumns = @JoinColumn(name = "user_id"), // Atributo da tabela pivô referente a esta entidade
            inverseJoinColumns = @JoinColumn(name = "role_id") // Atributo da tabela pivô referente a entidade role
    )
    private Set<Role> roles = new HashSet<>();

    // Os métodos sobrescritos abaixo são todos necessários uma vez que User 'implementa' UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    // Podemos personalizar os métodos sobrescritos da maneira que nos convém
    // Abaixo, retornamos o nome do usuário como seu primeiro nome e seu sobrenome
    // Isso pode ser usado para carregas as informações do usuário logado de maneira segura
    @Override
    public String getUsername() {
        String[] names = this.name.split(" ");

        String shortName = names[0];

        if (names.length > 1)
            shortName += " " + names[names.length - 1];

        return shortName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void merge(User updatedUser) {
        this.name = updatedUser.getName();
        this.email = updatedUser.getEmail();
    }

}
