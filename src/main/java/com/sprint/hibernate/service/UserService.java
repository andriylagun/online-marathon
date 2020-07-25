package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.entity.User;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUserById(BigInteger userId);

    User createOrUpdateUser(User input);

    List<User> getAllByRole (String role);

    boolean addUserToMarathon(User user, Marathon marathon);

}
