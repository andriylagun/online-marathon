package com.sprint.hibernate.repository;

import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


}
