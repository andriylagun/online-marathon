package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.repository.UserRepository;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUserById(long userId);

    User createOrUpdateUser(User input) throws NoSuchFieldException, IllegalAccessException;

    List<User> getAllByRole (String role);

    boolean addUserToMarathon(User user, Marathon marathon);

    List<User> allUsersByMarathonIdAndRole(long id, String role);

    void deleteUserById(long id);
    void deleteAll();

    public boolean deleteUserFromMarathon(User user, Marathon marathon);
}
