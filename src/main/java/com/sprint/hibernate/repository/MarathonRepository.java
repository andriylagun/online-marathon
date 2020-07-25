package com.sprint.hibernate.repository;

import com.sprint.hibernate.entity.Marathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
@Repository
public interface MarathonRepository extends JpaRepository<Marathon, Long> {
}
