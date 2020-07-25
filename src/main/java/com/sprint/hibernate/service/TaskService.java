package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.entity.Task;

import java.math.BigInteger;

public interface TaskService {
    Task createOrUpdateTask();
    boolean addTaskToSprint(Task task, Sprint sprint);
    Task   getTaskById	(long id);
    void deleteSprintById(long id);
}
