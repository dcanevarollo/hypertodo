package com.grupogbd.treinamento.hypertodo.services.task;

import com.grupogbd.treinamento.hypertodo.models.Task;
import com.grupogbd.treinamento.hypertodo.models.User;
import com.grupogbd.treinamento.hypertodo.repos.TaskRepository;
import com.grupogbd.treinamento.hypertodo.utils.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Douglas Canevarollo
 * @date : 26/04/2020
 */
@Service
public class LoadLoggedUserTasks {

    @Autowired
    private TaskRepository repository;

    public List<Task> execute() {
        User loggedUser = LoggedUser.getUser();

        return repository.findByUserOrderByDone(loggedUser);
    }

}
