package com.grupogbd.treinamento.hypertodo.exceptions;

/**
 * @author : Douglas Canevarollo
 * @date : 02/04/2020
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        // Mensagem da exceção lançada
        super("Já existe um usuário com esse e-mail");
    }
}
