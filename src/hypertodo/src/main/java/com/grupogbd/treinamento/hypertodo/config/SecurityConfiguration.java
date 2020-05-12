package com.grupogbd.treinamento.hypertodo.config;

import com.grupogbd.treinamento.hypertodo.services.user.LoadUserByUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
@Configuration  // Indica adições as configurações padrões do Spring Boot
@EnableWebSecurity  // Ativa o Spring Security
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoadUserByUsername loadUserByUsername;

    /**
     * Configura os acessos as rotas da aplicação web.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Qualquer toda com prefixo /resources e /plugins são permitidas a todos
                .antMatchers("/resources/**", "/plugins/**").permitAll()
                // A rota de login e de cadastro só são permitidas se o usuário não estiver logado
                .antMatchers("/", "/login", "/usuarios/cadastro/**").anonymous()
                // Qualquer outra rota só será permitida se o usuário estiver autenticado
                .anyRequest().authenticated()
                .and()
                // Informa qual a página de login e qual página deverá ser redirecionada em caso de sucesso
                .formLogin().loginPage("/login").defaultSuccessUrl("/tarefas", true)
                .and()
                // Analogamente, informa que a rota /logout serve para logout e que, em sucesso, retornar ao login
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    }

    /**
     * Informa ao Spring Security qual classe implementa a interface UserDetailService (para realização do login) e
     * qual ferramenta de criptografia de senhas será utilizada.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loadUserByUsername).passwordEncoder(new BCryptPasswordEncoder());
    }

}
