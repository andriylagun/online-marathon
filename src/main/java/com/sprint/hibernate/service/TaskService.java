package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.exceptions.TaskExistException;

import java.math.BigInteger;

public interface TaskService {
    Task createOrUpdateTask(Task task);
    boolean addTaskToSprint(Task task, Sprint sprint) throws TaskExistException;
    Task   getTaskById	(long id);
    void deleteAll();
    boolean checkTitle(String title);
}
