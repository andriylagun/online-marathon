package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.repository.TaskRepository;
import com.sprint.hibernate.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createOrUpdateTask() {
        return null;
    }

    @Override
    public boolean addTaskToSprint(Task task, Sprint sprint) {
        return sprint.getTasks().add(task);
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepository.getOne(id);
    }

    @Override
    public void deleteSprintById(long id) {
        taskRepository.deleteById(id);
    }
}
