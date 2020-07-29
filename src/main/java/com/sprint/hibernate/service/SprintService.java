package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Sprint;

import java.math.BigInteger;
import java.util.List;

public interface SprintService {
    List<Sprint> getSprintsByMarathonId(long id);

    boolean addSprintToMarathon(Sprint sprint, Marathon marathon);

    Sprint updateSprint(Sprint sprint);

    Sprint getSprintById(long id);

    void deleteSprintById(long id);
    void deleteAll();
}