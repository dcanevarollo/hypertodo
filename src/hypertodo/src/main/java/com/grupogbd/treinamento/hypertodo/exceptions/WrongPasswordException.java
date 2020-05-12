package com.grupogbd.treinamento.hypertodo.exceptions;

/**
 * @author : Douglas Canevarollo
 * @date : 02/04/2020
 */
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("A senha informada está incorreta");
    }
}
