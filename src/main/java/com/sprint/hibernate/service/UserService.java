package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Role;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.repository.UserRepository;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    User registerStudent(User input);

    List<User> getAll();

    User getUserById(long userId);

    User createOrUpdateUser(User input);

    List<User> getAllByRoleId (long roleId);

    boolean addUserToMarathon(User user, Marathon marathon);

    List<User> allUsersByMarathonIdAndRoleId(long id, long roleId);

    void deleteUserById(long id);
    void deleteAll();

    boolean deleteUserFromMarathon(User user, Marathon marathon);

    boolean checkEmail(String email);
}
