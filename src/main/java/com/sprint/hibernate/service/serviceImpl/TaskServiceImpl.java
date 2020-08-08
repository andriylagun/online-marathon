package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.exceptions.TaskExistException;
import com.sprint.hibernate.repository.TaskRepository;
import com.sprint.hibernate.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@PreAuthorize("hasAuthority('MENTOR')")
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createOrUpdateTask(Task task) {
        if (task != null) {
            Optional<Task> temp = taskRepository.findById(task.getId());

            if (temp.isPresent()) {
                Task newTask = temp.get();
                newTask.setTitle(task.getTitle());
                newTask.setCreated(task.getCreated());
                newTask.setUpdated(task.getUpdated());
                newTask.setProgresses(task.getProgresses());
                newTask.setSprint(task.getSprint());
                newTask = taskRepository.save(newTask);
                return newTask;
            }
        }
        return taskRepository.save(task);
    }

    @Override
    public boolean addTaskToSprint(Task task, Sprint sprint) {
        if (checkTitle(task.getTitle())) {
            throw new TaskExistException("Task with this title already exists");
        }
        task.setSprint(sprint);
        taskRepository.save(task);
        return true;
    }

    @Override
    public boolean checkTitle(String title) {
        return taskRepository.findTaskByTitle(title) != null;
    }

    public List<Task> getAllTasksBySpringId(long id){ return taskRepository.getTasksBySprintId(id); }
    @Override
    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElseGet(null);
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }
}
