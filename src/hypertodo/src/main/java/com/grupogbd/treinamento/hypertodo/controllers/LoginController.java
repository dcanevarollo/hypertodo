package com.grupogbd.treinamento.hypertodo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
@Controller  // Informa ao Spring que a classe é um controller
public class LoginController {

    // URLs da requisição
    // Quando o usuário digitar uma dessas strings no navegador, esse método será ativado
    // @GetMapping significa que é uma requisição HTTP GET para "http(s)://baseURL/" ou "http(s)://baseURL/login"
    // baseURL é a url base da aplicação e depende do ambiente. Em desenvolvimento, será localhost:8080
    // Em produção, nomedosite.com.br, por exemplo
    @GetMapping({ "/", "/login" })
    // ModelAndView é uma classe do Spring MVC que permite retornar uma página HTML (view) junto a um modelo de dados
    // (Model). Esses dados poderão ser acessados diretamente com o Thymeleaf
    public ModelAndView index() {
        // Retorna a view de login
        // A string "login/index" é o caminho para o arquivo .html
        return new ModelAndView("login/index");
    }

}
