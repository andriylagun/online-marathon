package com.sprint.hibernate.repository;

import com.sprint.hibernate.entity.Marathon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface MarathonRepository extends JpaRepository<Marathon, BigInteger> {
    List<Marathon> getAll();
    Marathon  getMarathonById (BigInteger id);
    Marathon createOrUpdate(Marathon marathon);
    void deleteMarathonById(BigInteger id);
}
