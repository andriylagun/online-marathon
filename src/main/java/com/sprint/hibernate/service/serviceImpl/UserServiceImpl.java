package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.repository.MarathonRepository;
import com.sprint.hibernate.repository.UserRepository;
import com.sprint.hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private MarathonRepository marathonRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMarathonRepository(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }



    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    public User getUserById(BigInteger userId) {            //long?  UUID?
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException("No user exist for given id");
        }
    }

    public User createOrUpdateUser(User input) {
        if(input.getId() != null) {
            Optional<User> user = userRepository.findById(input.getId());

            if(user.isPresent()) {
                User newUser = user.get();
                newUser.setEmail(input.getEmail());
                newUser.setFirstName(input.getFirstName());
                newUser.setLastName(input.getLastName());
                newUser.setRole(input.getRole());
                newUser.setPassword(input.getPassword());
                newUser = userRepository.save(newUser);
                return newUser;
            }
        }
        return userRepository.save(input);
    }

    public List<User> getAllByRole (String role) {
        return userRepository.getAllByRole(User.Role.valueOf(role.toUpperCase()));
    }

    public boolean addUserToMarathon(User user, Marathon marathon) {
//        User userEntity = userRepository.getOne(user.getId());
//        Marathon marathonEntity = marathonRepository.getMarathonById(marathon.getId());
//        List<User> users = marathonEntity.getUsers();
//        users.add(userEntity);
//        marathon.setUsers(users);
//        marathonRepository.save(marathon);

        return true;
    }

    public boolean addUserToTask(User user, Task task) {
        return true;
    }
}
