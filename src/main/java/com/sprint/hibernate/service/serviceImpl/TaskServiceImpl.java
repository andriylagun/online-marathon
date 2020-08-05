package com.sprint.hibernate.service.serviceImpl;
import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.repository.TaskRepository;
import com.sprint.hibernate.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
        if(task != null) {
            Optional<Task> temp = taskRepository.findById( task.getId());

            if(temp.isPresent()) {
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
        task.setSprint(sprint);
        taskRepository.save(task);
        return true;
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElseGet(null);
    }
    public void deleteAll(){
        taskRepository.deleteAll();
    }
}
