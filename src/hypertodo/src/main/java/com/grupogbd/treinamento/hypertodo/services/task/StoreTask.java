package com.grupogbd.treinamento.hypertodo.services.task;

import com.grupogbd.treinamento.hypertodo.models.Task;
import com.grupogbd.treinamento.hypertodo.repos.TaskRepository;
import com.grupogbd.treinamento.hypertodo.utils.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Douglas Canevarollo
 * @date : 26/04/2020
 */
@Service
public class StoreTask {

    @Autowired
    private TaskRepository repository;

    public void execute(Task task) {
        task.setUser(LoggedUser.getUser());

        repository.save(task);
    }

}
