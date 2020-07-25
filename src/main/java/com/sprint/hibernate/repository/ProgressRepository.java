package com.sprint.hibernate.repository;

import com.sprint.hibernate.entity.Progress;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, BigInteger> {

   // @Query(value = "SELECT p FROM Progress p WHERE p.trainee.id = :userId AND p. = :marathonId")
    List<Progress> allProgressByUserIdAndMarathonId(BigInteger userId, BigInteger marathonId);  //long? UUID?

    //@Query(value = "SELECT p FROM Progress p WHERE p.trainee.id = :userId AND p. = :sprintId")
    List<Progress> allProgressByUserIdAndSprintId(BigInteger userId, BigInteger sprintId);  // long? UUID?

}
