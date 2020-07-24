package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.User;
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

    @Autowired  //Нам потрібен сеттер, або конструктор щоб визначати що саме ми будемо використовувати
                //@Autowired нам пошукає цей репозиторій, можна final,але тоді потрібне точне приведення до new UserRepository
                //Ми ж стараємось уникати new.
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // final private MarathonRepository marathonRepository;

  //  public UserServiceImpl (UserRepository userRepository, MarathonRepository marathonRepository) {
  //      this.userRepository = userRepository;
  //      this.marathonRepository = marathonRepository;
  //  }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty()?new ArrayList<>():users;
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
