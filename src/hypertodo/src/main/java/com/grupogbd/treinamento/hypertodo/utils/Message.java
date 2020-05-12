package com.grupogbd.treinamento.hypertodo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 *
 * Auxilia na criação de mensagens para retorno dos controllers.
 */
@AllArgsConstructor
@Getter
@Setter
public class Message {

    private String title;

    private String message;

    private String type;

    public static Message internalError() {
        return new Message(
                "Erro interno!",
                "Contate um administrador do sistema.",
                "error"
        );
    }

    public static Message fieldsErrors() {
        return new Message(
                "Erro!",
                "Por favor, verifique os dados preenchidos.",
                "error"
        );
    }

    public static Message successMessage(String message) {
        return new Message(
                "Sucesso!",
                message + "!",
                "success"
        );
    }

    public static Message errorMessage(String message) {
        return new Message(
                "Erro!",
                message + ".",
                "error"
        );
    }

}
