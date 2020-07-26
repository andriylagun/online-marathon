package com.sprint.hibernate.repository;

import com.sprint.hibernate.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    @Query(value = "  SELECT p FROM Progress p INNER JOIN Marathon m ON m.id=:marathonId WHERE p.trainee.id=:userId")
    List<Progress> allProgressByUserIdAndMarathonId(long userId, long marathonId);

    @Query(value = "SELECT p FROM Progress p INNER JOIN Task t ON t.sprint.id=:sprintId WHERE p.trainee.id = :userId")
    List<Progress> allProgressByUserIdAndSprintId(long userId, long sprintId);

}
