package com.sprint.hibernate.controller;

import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.service.SprintService;
import com.sprint.hibernate.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Data
@AllArgsConstructor
public class TaskController {

    TaskService taskService;
    SprintService sprintService;

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
}
