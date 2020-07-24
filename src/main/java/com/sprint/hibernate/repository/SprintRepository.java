package com.sprint.hibernate.repository;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint, BigInteger> {
    List<Sprint> getSprintsByMarathonId(BigInteger id);
    boolean addSprintToMarathon(Sprint sprint, Marathon marathon);
    boolean updateSprint(Sprint sprint);
    Sprint  getSprintById(BigInteger id);
}
