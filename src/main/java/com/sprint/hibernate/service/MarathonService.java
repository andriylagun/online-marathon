package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.User;

import java.util.List;

public interface MarathonService {
    List<Marathon> getAll();
    Marathon  getMarathonById (long id);
    Marathon createOrUpdate(Marathon marathon);
    void deleteMarathonById(long id);
    void deleteAll();
}
