package com.grupogbd.treinamento.hypertodo.models.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author : Douglas Canevarollo
 * @date : 02/04/2020
 */
// Auxilia na requisição de uma nova senha, armazenando a antiga e nova senha.
// @Component informa ao Spring que essa classe é um componente auxiliar
@Component
@Data
public class NewPassword {

    private String currentPassword;

    private String newPassword;

}
