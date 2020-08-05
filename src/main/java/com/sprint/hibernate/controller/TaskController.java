package com.sprint.hibernate.controller;

import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.exceptions.TaskExistException;
import com.sprint.hibernate.service.SprintService;
import com.sprint.hibernate.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;
    private SprintService sprintService;
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @GetMapping("/tasks/{sprint_id}")
    public String allTasks(@PathVariable(name = "sprint_id") long sprintId, Model model) {
        Sprint sprint = sprintService.getSprintById(sprintId);
        Task newTask = new Task();
        List<Task> tasks = sprint.getTasks();
        model.addAttribute("newTask", newTask);
        model.addAttribute("tasks", tasks);
        model.addAttribute("sprint", sprint);
        return "tasks";
    }

    @PostMapping("tasks/add/{sprint_id}")
    public String addTaskToSprint(@PathVariable(name = "sprint_id") long sprint_id,
                                  @ModelAttribute(name = "newTask") Task task) throws TaskExistException {
        logger.info("Adding task to sprint");
        taskService.addTaskToSprint(task, sprintService.getSprintById(sprint_id));
        return "redirect:/tasks/{sprint_id}";
    }

}
