package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.Marathon;

import java.math.BigInteger;
import java.util.List;

public interface MarathonService {
    List<Marathon> getAll();
    Marathon  getMarathonById (BigInteger id);
    Marathon createOrUpdate(Marathon marathon);
    void deleteMarathonById(BigInteger id);
}
