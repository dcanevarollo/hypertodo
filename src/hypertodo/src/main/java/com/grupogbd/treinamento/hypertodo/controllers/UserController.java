package com.grupogbd.treinamento.hypertodo.controllers;

import com.grupogbd.treinamento.hypertodo.models.User;
import com.grupogbd.treinamento.hypertodo.models.utils.NewPassword;
import com.grupogbd.treinamento.hypertodo.services.user.StoreUser;
import com.grupogbd.treinamento.hypertodo.services.user.UpdatePassword;
import com.grupogbd.treinamento.hypertodo.services.user.UpdateUser;
import com.grupogbd.treinamento.hypertodo.utils.LoggedUser;
import com.grupogbd.treinamento.hypertodo.utils.Message;
import com.grupogbd.treinamento.hypertodo.validators.NewPasswordValidator;
import com.grupogbd.treinamento.hypertodo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
@Controller
@RequestMapping("/usuarios") // Toda requisição para este controller terá o prefixo '/usuarios'
public class UserController {

    // O @Autowire instancia um objeto de StoreUser, por exemplo, para qualquer método desta classe
    // Isso faz com que não precisamos escrever, por exemplo, StoreUser storeUser = new StoreUser() em cada
    // método para utilizarmos o repository
    // Mas a principal necessidade é para que a interface do repositório de usuários dentro do service seja instanciada
    // pelo Spring
    @Autowired
    private StoreUser storeUser;

    @Autowired
    private UpdateUser updateUser;

    @Autowired
    private UpdatePassword updatePassword;

    /**
     * Define a classe que fará as validações do formulário de cadastro/edição de usuários.
     */
    @InitBinder("user") // Nome do binder deve ser o mesmo da variável com @Valid
    public void initUserBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    /**
     * GET /usuarios/cadastro
     *
     * @param user objecto a ser carregado na view com o model
     * @return a página de cadastro de usuários
     */
    @GetMapping("/cadastro")
    public ModelAndView showSignUpPage(User user) {
        // Será possível acessar os atributos da classe User na view abaixo
        // Pois adicionamos um objeto User a ela
        return new ModelAndView("users/create").addObject("user", user);
    }

    /**
     * Cria/salva um novo usuário na base de dados
     * POST /usuarios/cadastro/salvar
     *
     * @param user dados do formulário preenchido pelo usuário
     * @param result resultado da validação
     */
    @PostMapping("/cadastro/salvar")
    // BindingResult sempre deve vir depois do objeto a ser validado
    // É o objeto result que conterá os erros capturados pelas validações
    public ModelAndView store(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        // Retorna à página de cadastro com uma mensagem se houver erros
        if (result.hasErrors())
            return showSignUpPage(user).addObject("message", Message.fieldsErrors());

        // Qualquer método num controller tem essa forma: uma tratativa de exceções com try - catch
        // O método invocará o service que fará toda a lógica de negócio para processar a requisição
        // e retornará uma mensagem à view
        // Qualquer exceção lançada pelo service é capturada pelos 'catchs' abaixo
        try {
            storeUser.execute(user);
            redirect.addFlashAttribute("message", Message.successMessage("Você foi cadastrado"));

            // O "redirect:" redireciona para a view informada e "reseta" as propriedades do model
            // Assim, para retornar uma mensagem de sucesso ao usuário, usamos redirect.addFlashAttribute()
            // No redirecionamento, não passamos o .html como parâmetro, mas sim a rota definida para este .html
            return new ModelAndView("redirect:/login");
        } catch (RuntimeException error) {
            // Exceções conhecidas são capturadas pelo RuntimeException
            // Nesse caso, o usuário pode tentar se cadastrar com um e-mail já existente
            // Como sabemos que isso pode ocorrer, lançamos uma UserAlreadyExistsException, definida dentro da pasta
            // exceptions/
            return showSignUpPage(user).addObject("message", Message.errorMessage(error.getMessage()));
        } catch (Exception error) {
            // Erros desconhecidos são um mistério. Assim, capturamos esse erro e printamos ele no log do servidor
            // para futura análise dos desenvolvedores
            error.printStackTrace();

            // Então, retornamos a view com uma mensagem informando o ocorrido
            return showSignUpPage(user).addObject("message", Message.internalError());
        }
    }

    /**
     * Edição do usuário logado
     * GET /usuarios/editar
     *
     * @param user usuário carregado como model na view (será o usuário logado)
     * @param newPassword objeto auxiliar para atualização de senha
     * @return página de edição do usuário logado
     */
    @GetMapping("/editar")
    public ModelAndView showEditPage(User user, NewPassword newPassword) {
        // Carrega o usuário logado se o objeto do parâmetro estiver vazio
        user = (user == null || user.getId() == 0) ? LoggedUser.getUser() : user;

        // O mesmo pro objeto newPassword, pois a página pode ser chamada pela atualização de senha
        // ou pela atualização de dados pessoais
        newPassword = newPassword != null ? newPassword : new NewPassword();

        return new ModelAndView("users/edit")
                .addObject("user", user)
                .addObject("newPassword", newPassword);
    }

    /**
     * Edita os dados pessoais do usuário logado
     * POST /usuarios/editar/salvar
     *
     * @param user objeto com os novos dados do usuário
     * @param result resultado da validação
     */
    @PostMapping("/editar/salvar")
    public ModelAndView update(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors())
            return showEditPage(user, null).addObject("message", Message.fieldsErrors());

        try {
            updateUser.execute(user);
            redirect.addFlashAttribute("message", Message.successMessage("Dados atualizados"));

            return new ModelAndView("redirect:/usuarios/editar");
        } catch (RuntimeException error) {
            return showEditPage(user, null).addObject("message", Message.errorMessage(
                    error.getMessage()
            ));
        } catch (Exception error) {
            error.printStackTrace();

            return showEditPage(user, null).addObject("message", Message.internalError());
        }
    }

    /**
     * Atualiza a senha do usuário logado
     * POST /usuarios/editar/senha
     *
     * @param newPassword model auxiliar contendo a antiga e nova senha
     * @param result resultado da validação
     */
    @PostMapping("/editar/senha")
    public ModelAndView updatePassword(
            @Valid NewPassword newPassword,
            BindingResult result,
            RedirectAttributes redirect) {
        if (result.hasErrors())
            return showEditPage(null, newPassword).addObject("message", Message.fieldsErrors());

        try {
            updatePassword.execute(newPassword);
            redirect.addFlashAttribute("message", Message.successMessage("Senha atualizada"));

            return new ModelAndView("redirect:/usuarios/editar");
        } catch (RuntimeException error) {
            return showEditPage(null, newPassword).addObject("message", Message.errorMessage(
                    error.getMessage()
            ));
        } catch (Exception error) {
            error.printStackTrace();

            return showEditPage(null, newPassword).addObject("message", Message.internalError());
        }
    }

}
