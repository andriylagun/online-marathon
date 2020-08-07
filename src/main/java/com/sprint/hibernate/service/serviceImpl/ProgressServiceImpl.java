package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Progress;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.repository.ProgressRepository;
import com.sprint.hibernate.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.sprint.hibernate.entity.Progress.TaskStatus.PENDING;

@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private ProgressRepository progressRepository;

    @Autowired
    public void setProgressRepository(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Override
    public Progress getProgressById(long progressId) {
        Optional<Progress> progress = progressRepository.findById(progressId);

        if (progress.isPresent()) {
            return progress.get();
        } else {
            throw new EntityNotFoundException("No progress exist for given id");
        }
    }

    @Override
    public Progress addTaskForStudent(Task task, User user) {
        Progress progress = new Progress();
        progress.setTask(task);
        progress.setTrainee(user);
        progress.setStarted(task.getCreated());
        progress.setUpdated(task.getUpdated());
        progress.setStatus(PENDING);
        progressRepository.save(progress);

        return progress;
    }

    @Override
    public Progress addOrUpdateProgress(Progress input) {
        if (input != null) {
            Optional<Progress> progress = progressRepository.findById(input.getId());

            if (progress.isPresent()) {
                Progress newProgress = progress.get();
                newProgress.setStatus(input.getStatus());
                newProgress.setStarted(input.getStarted());
                newProgress.setUpdated(LocalDate.now());
                return newProgress;
            }
        }
        return progressRepository.save(input);

    }

    @Override
    public boolean setStatus(Progress.TaskStatus taskStatus, Progress progress) {
        Optional<Progress> progressEntity = progressRepository.findById(progress.getId());
        if (progressEntity.isPresent()) {
            progressEntity.get().setStatus(taskStatus);
            return true;
        }
        return false;
    }

    @Override
    public List<Progress> allProgressByUserIdAndMarathonId(long userId, long marathonId) {
        return progressRepository.findAllByTraineeIdAndTaskSprintMarathonId(userId, marathonId);
    }

    @Override
    public List<Progress> allProgressByUserIdAndSprintId(long userId, long sprintId) {
        return progressRepository.findAllByTraineeIdAndTaskSprintId(userId, sprintId);
    }

    @Override
    public void deleteProgressById(long id) {
        progressRepository.deleteById(id);
    }

    public void deleteAll() {
        progressRepository.deleteAll();
    }
}
