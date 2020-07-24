package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Progress;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.repository.ProgressRepository;
import com.sprint.hibernate.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private ProgressRepository progressRepository;

    @Autowired
    public void setProgressRepository (ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public Progress getProgressById(BigInteger progressId) {
        return null;
    }

    public Progress addTaskForStudent(Task task, User user) {
        return null;
    }

    public Progress addOrUpdateProgress(Progress progress) {
        return null;
    }

    public boolean setStatus(Progress.TaskStatus taskStatus, Progress progress) {
        return true;
    }

    public List<Progress> allProgressByUserIdAndMarathonId(BigInteger userId, BigInteger marathonId) {
        return null;
    }

    public List<Progress> allProgressByUserIdAndSprintId(BigInteger userId, BigInteger sprintId) {
        return null;
    }

}
