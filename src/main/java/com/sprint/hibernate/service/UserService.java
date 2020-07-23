package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.User;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUserById(BigInteger userId);

}
