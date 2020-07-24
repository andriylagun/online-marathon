package com.sprint.hibernate.service;

import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.repository.UserRepository;
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

    final private UserRepository userRepository;
    final private MarathonRepository marathonRepository;

    public UserServiceImpl (UserRepository userRepository, MarathonRepository marathonRepository) {
        this.userRepository = userRepository;
        this.marathonRepository = marathonRepository;
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();

        if(!users.isEmpty()) {
            return users;
        }

        return new ArrayList<>();
    }

    public User getUserById(BigInteger userId) {            //long?  UUID?
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException("No user exist for given id");
        }
    }

//    public User createOrUpdateUser(User user) {
//
//    }
}
