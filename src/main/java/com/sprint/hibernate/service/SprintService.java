package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.exceptions.SprintExistException;

import java.util.List;

public interface SprintService {
    List<Sprint> getSprintsByMarathonId(long id);

    boolean addSprintToMarathon(Sprint sprint, Marathon marathon) throws SprintExistException;

    Sprint updateSprint(Sprint sprint);

    Sprint getSprintById(long id);

    void deleteSprintById(long id,Marathon marathon);
    void deleteAll();

    boolean checkTitle(String title);
}