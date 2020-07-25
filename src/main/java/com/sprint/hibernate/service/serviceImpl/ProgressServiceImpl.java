package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.*;
import com.sprint.hibernate.repository.*;
import com.sprint.hibernate.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sprint.hibernate.entity.Progress.TaskStatus.*;

@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private ProgressRepository progressRepository;

    @Autowired
    public void setProgressRepository (ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public Progress getProgressById(long progressId) {
        Optional<Progress> progress = progressRepository.findById(progressId);

        if (progress.isPresent()) {
            return progress.get();
        } else {
            throw new EntityNotFoundException("No progress exist for given id");
        }
    }

    public Progress addTaskForStudent(Task task, User user) {
        Progress progress = new Progress();
        progress.setTask(task);
        progress.setTrainee(user);
        progress.setStarted(task.getCreated());
        progress.setUpdated(task.getUpdated());
        progress.setStatus(PENDING);             //???????????????????
        progressRepository.save(progress);

        return progress;
    }

    public Progress addOrUpdateProgress(Progress input) {
        if(input!= null) {
            Optional<Progress> progress = progressRepository.findById(input.getId());

            if(progress.isPresent()) {
                Progress newProgress = progress.get();
                newProgress.setStatus(input.getStatus());
                newProgress.setStarted(input.getStarted());
                newProgress.setUpdated(LocalDate.now());
                return newProgress;
            }
        }
        return progressRepository.save(input);

    }

    public boolean setStatus(Progress.TaskStatus taskStatus, Progress progress) {
        Optional<Progress> progressEntity = progressRepository.findById(progress.getId());
        if (progressEntity.isPresent()) {
            progressEntity.get().setStatus(taskStatus);
            return true;
        }
        return false;
    }

    public List<Progress> allProgressByUserIdAndMarathonId(long userId, long marathonId) {
        return progressRepository.allProgressByUserIdAndMarathonId(userId, marathonId);
    }

    public List<Progress> allProgressByUserIdAndSprintId(long userId, long sprintId) {
//        List<Task> tasks = sprintRepository.getOne(sprintId).getTasks();
//        return tasks.stream()
//                .map(task -> task.getProgress())
//                .flatMap(progress -> progress.stream())
//                .filter(progress -> progress.getTrainee().getId() == userId)
//                .collect(Collectors.toList());
        return progressRepository.allProgressByUserIdAndSprintId(userId, sprintId);
    }

}
