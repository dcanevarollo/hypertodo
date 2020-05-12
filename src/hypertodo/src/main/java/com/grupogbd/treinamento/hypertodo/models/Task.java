package com.grupogbd.treinamento.hypertodo.models;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
@Entity  // Define a classe como uma entidade do banco de dados
@Data  // Anotação do Lombok; construtores, getters e setters implícitos
// Por padrão, o nome da classe é o mesmo da tabela no banco
public class Task {

    @Id  // Indica que o atributo é o id da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // O id deve ser gerado automaticamente
    private int id;

    @Column(nullable = false)  // A coluna não pode ser nula
    private String title;

    // Se o atributo é simples e o nome é o mesmo tanto no model quanto na base, não é necessário usar @Column
    private String description;

    @Column(name = "is_done")  // O nome do atributo na tabela não é o mesmo, então passamos o nome na anotação @Column
    private boolean done = false;  // Por padrão, tarefas são criadas como "não concluídas"

    @ManyToOne  // N:1 com usuários
    @JoinColumn(name = "user_id", nullable = false) // Chave estrangeira da tabela task
    private User user;  // O usuário a quem a tarefa pertence

}
