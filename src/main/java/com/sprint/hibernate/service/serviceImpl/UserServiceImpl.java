package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Progress;
import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.exceptions.EmailExistException;
import com.sprint.hibernate.exceptions.MarathonExistException;
import com.sprint.hibernate.repository.MarathonRepository;
import com.sprint.hibernate.repository.ProgressRepository;
import com.sprint.hibernate.repository.UserRepository;
import com.sprint.hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private MarathonRepository marathonRepository;
    private ProgressRepository progressRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMarathonRepository(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }
    @Autowired
    public void setProgressRepository(ProgressRepository progressRepository){
        this.progressRepository = progressRepository;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public User getUserById(long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException("No user exist for given id");
        }
    }


    @Override
    public User createOrUpdateUser(User input) {
        if(input != null) {
                Optional<User> user = userRepository.findById(input.getId());

            if(user.isPresent()) {
                User newUser = user.get();
                newUser.setEmail(input.getEmail());
                newUser.setFirstName(input.getFirstName());
                newUser.setLastName(input.getLastName());
                newUser.setRole(input.getRole());
                newUser.setPassword(input.getPassword());
                return userRepository.save(newUser);
            }
        }
        if(checkEmail(input.getEmail())) {
            throw new EmailExistException("User with this email is already exist");
        }
        return userRepository.save(input);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepository.findStudentByEmail(email)!=null;
    }

    @Override
    public List<User> getAllByRole (String role) {
        return userRepository.getAllByRole(User.Role.valueOf(role.toUpperCase()));
    }

    @Override
    public boolean addUserToMarathon(User user, Marathon marathon) {

        Optional<User> userEntity = userRepository.findById(user.getId());
        Optional<Marathon> marathonEntity = marathonRepository.findById(marathon.getId());
        if(!userEntity.isPresent() || !marathonEntity.isPresent()) {
            return false;
        }
        List<User> users = marathonEntity.get().getUsers();
        users.add(userEntity.get());
        marathonEntity.get().setUsers(users);
        return true;
    }

    public List<User> allUsersByMarathonIdAndRole(long id, String role) {
        return userRepository.findAllByMarathonsIdAndRole(id, User.Role.valueOf(role));
    }

    @Override
    public void deleteUserById(long id) {
        User user = userRepository.getOne(id);
        List<User> students;
        List<Marathon> marathons = user.getMarathons();
        if (marathons != null) {
            for (Marathon marathon : marathons) {
                deleteUserFromMarathon(user, marathon);
            }
        }
        List<Progress> userProgress= progressRepository.findAllByTraineeId(user.getId());
        if(userProgress != null){
            for (Progress progress: userProgress)
            {
                progress.setTrainee(null);
            }
        }
        userRepository.deleteById(id);
    }
    public void deleteAll(){
        userRepository.deleteAll();
    }

    @Override
    public boolean deleteUserFromMarathon(User user, Marathon marathon) {

        Optional<User> userEntity = userRepository.findById(user.getId());
        Optional<Marathon> marathonEntity = marathonRepository.findById(marathon.getId());
        if(!userEntity.isPresent() || !marathonEntity.isPresent()) {
            return false;
        }
        List<User> users = marathonEntity.get().getUsers();
        users.remove(userEntity.get());
        marathonEntity.get().setUsers(users);
        return true;
    }
}
