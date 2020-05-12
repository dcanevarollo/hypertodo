package com.grupogbd.treinamento.hypertodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@EnableJpaRepositories("com.grupogbd.treinamento.hypertodo.repos")  // Indica onde encontrar os repositórios
@SpringBootApplication  // Aplica todas as configurações padrões com o Spring Boot
public class HypertodoApplication {

    /**
     * Inicializa a aplicação.
     */
    public static void main(String[] args) {
        SpringApplication.run(HypertodoApplication.class, args);
    }

    /**
     * Indica para o Spring qual ferramenta será utilizada para criptografar as senhas no momento de salvar um usuário
     * no banco de dados.
     *
     * @return {@link BCryptPasswordEncoder} como ferramenta de criptografia de senhas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Define a localização padrão para pt_BR (português brasileiro) para que o Spring consiga identificar as mensagens
     * no arquivo message.properties.
     *
     * @return {@link LocaleResolver} configurado para pt_BR.
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();

        localeResolver.setDefaultLocale(Locale.forLanguageTag("pt_BR"));

        return localeResolver;
    }

}
