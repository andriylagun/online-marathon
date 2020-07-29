package com.sprint.hibernate.repository;

import com.sprint.hibernate.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    List<Progress> findAllByTraineeIdAndTaskSprintMarathonId(long userId, long marathonId);

    List<Progress> findAllByTraineeIdAndTaskSprintId(long userId, long sprintId);

    List<Progress> findAllByTraineeId(long userId);

}
