package com.grupogbd.treinamento.hypertodo.services.task;

import com.grupogbd.treinamento.hypertodo.repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Douglas Canevarollo
 * @date : 31/03/2020
 */
@Service
public class DeleteTask {

    @Autowired
    private TaskRepository repository;

    public void execute(int id) {
        repository.deleteById(id);
    }

}
