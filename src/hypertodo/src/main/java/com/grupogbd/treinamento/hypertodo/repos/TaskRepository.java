package com.grupogbd.treinamento.hypertodo.repos;

import com.grupogbd.treinamento.hypertodo.models.Task;
import com.grupogbd.treinamento.hypertodo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Douglas Canevarollo
 * @date : 31/03/2020
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Task> findByUserOrderByDone(User user);

}
