package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.Progress;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.entity.User;

import java.math.BigInteger;
import java.util.List;

public interface ProgressService {

    Progress getProgressById(BigInteger progressId);

    Progress addTaskForStudent(Task task, User user);

    Progress addOrUpdateProgress(Progress progress);

    boolean setStatus(Progress.TaskStatus taskStatus, Progress progress);

    List<Progress> allProgressByUserIdAndMarathonId(BigInteger userId, BigInteger marathonId);

    List<Progress> allProgressByUserIdAndSprintId(BigInteger userId, BigInteger sprintId);

}
