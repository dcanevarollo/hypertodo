package com.grupogbd.treinamento.hypertodo.controllers;

import com.grupogbd.treinamento.hypertodo.models.Task;
import com.grupogbd.treinamento.hypertodo.services.task.DeleteTask;
import com.grupogbd.treinamento.hypertodo.services.task.FinishTask;
import com.grupogbd.treinamento.hypertodo.services.task.LoadLoggedUserTasks;
import com.grupogbd.treinamento.hypertodo.services.task.StoreTask;
import com.grupogbd.treinamento.hypertodo.utils.Message;
import com.grupogbd.treinamento.hypertodo.validators.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Douglas Canevarollo
 * @date : 12/03/2020
 */
@Controller
@RequestMapping("/tarefas")
public class TaskController {

    @Autowired
    private LoadLoggedUserTasks loadLoggedUserTasks;

    @Autowired
    private StoreTask storeTask;

    @Autowired
    private FinishTask finishTask;

    @Autowired
    private DeleteTask deleteTask;

    @InitBinder("task")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new TaskValidator());
    }

    /**
     * Exibe a página de tarefas do usuário logado.
     * GET /tarefas
     */
    @GetMapping
    public ModelAndView showTasksPage(Task task) {
        List<Task> tasks = loadLoggedUserTasks.execute();

        return new ModelAndView("tasks/index")
                .addObject("tasks", tasks)
                .addObject("task", task);
    }

    /**
     * Cria/salva uma nova tarefa na base de dados
     * POST /tarefas/nova
     *
     * @param task objeto contendo os dados da tarefa
     * @param result resultado da validação
     */
    @PostMapping("/nova")
    public ModelAndView store(@Valid Task task, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors())
            return showTasksPage(task).addObject("message", Message.fieldsErrors());

        try {
            storeTask.execute(task);
            redirect.addFlashAttribute("message", Message.successMessage("Tarefa adicionada"));

            return new ModelAndView("redirect:/tarefas");
        } catch (Exception error) {
            error.printStackTrace();

            return showTasksPage(task).addObject("message", Message.internalError());
        }
    }

    /**
     * Finaliza uma tarefa com id.
     * POST /tarefas/finalizar/:id
     *
     * @param id id da tarefa a ser finalizada
     */
    @PostMapping("/finalizar/{id}")
    public ModelAndView finish(@PathVariable("id") int id, RedirectAttributes redirect) {
        try {
            finishTask.execute(id);
            redirect.addFlashAttribute("message", Message.successMessage("Tarefa finalizada"));

        } catch (Exception error) {
            error.printStackTrace();
            redirect.addFlashAttribute("message", Message.internalError());
        }

        return new ModelAndView("redirect:/tarefas");
    }

    @PostMapping("/remover/{id}")
    public ModelAndView delete(@PathVariable("id") int id, RedirectAttributes redirect) {
        try {
            deleteTask.execute(id);
            redirect.addFlashAttribute("message", Message.successMessage("Tarefa removida"));
        } catch (Exception error) {
            error.printStackTrace();
            redirect.addFlashAttribute("message", Message.internalError());
        }

        return new ModelAndView("redirect:/tarefas");
    }

}
