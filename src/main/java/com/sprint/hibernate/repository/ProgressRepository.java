package com.sprint.hibernate.repository;

import com.sprint.hibernate.entity.Progress;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, BigInteger> {

    Progress getProgressById(BigInteger progressId); //long? UUID?

    Progress addTaskForStudent(Task task, User user);

    Progress addOrUpdateProgress(Progress progress);

    boolean setStatus(Progress.TaskStatus taskStatus, Progress progress);

    List<Progress> allProgressByUserIdAndMarathonId(BigInteger userId, BigInteger marathonId);  //long? UUID?

    List<Progress> allProgressByUserIdAndSprintId(BigInteger userId, BigInteger sprintId);  // long? UUID?

}
