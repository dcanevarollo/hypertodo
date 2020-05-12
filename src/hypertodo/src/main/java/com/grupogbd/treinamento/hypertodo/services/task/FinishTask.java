package com.grupogbd.treinamento.hypertodo.services.task;

import com.grupogbd.treinamento.hypertodo.models.Task;
import com.grupogbd.treinamento.hypertodo.repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Douglas Canevarollo
 * @date : 26/04/2020
 */
@Service
public class FinishTask {

    @Autowired
    private TaskRepository repository;

    public void execute(int id) throws Exception {
        Task task = repository.findById(id).orElseThrow(Exception::new);

        task.setDone(true);

        repository.save(task);
    }

}
