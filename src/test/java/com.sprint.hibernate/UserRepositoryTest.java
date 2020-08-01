package com.sprint.hibernate;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.repository.MarathonRepository;
import com.sprint.hibernate.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MarathonRepository marathonRepository;


    private static User student1;
    private static User student2;
    private static User student3;
    private static User mentor1;
    private static User mentor2;
    private static Marathon marathon1;

    @BeforeEach
    public void setUp() {
        marathon1 = Marathon.builder().id(1).title("JOM_1").build();
        marathonRepository.save(marathon1);
        student1 = User.builder().firstName("Uliana")
                .id(1)
                .lastName("Tomyn")
                .email("uliana@gmail.com")
                .password("password1")
                .role(User.Role.TRAINEE)
                .build();
        student2 = User.builder()
                .id(2)
                .firstName("Andriy")
                .lastName("Lagun")
                .email("andriy@gmail.com")
                .password("password2")
                .role(User.Role.TRAINEE)
                .build();
        student3 = User.builder()
                .id(3)
                .firstName("Olesia")
                .lastName("Setrina")
                .email("olesia@gmail.com")
                .password("password3")
                .role(User.Role.TRAINEE)
                .build();
        mentor1 = User.builder()
                .id(4)
                .firstName("Mykola")
                .lastName("Demchyna")
                .email("mykola@gmail.com")
                .password("password4")
                .role(User.Role.MENTOR)
                .build();
        mentor2 = User.builder()
                .id(5)
                .firstName("Nataliia")
                .lastName("Romanenko")
                .email("nataliia@gmail.com")
                .password("password5")
                .role(User.Role.MENTOR)
                .build();

        student1.setMarathons(List.of(marathon1));
        student2.setMarathons(List.of(marathon1));
        mentor1.setMarathons(List.of(marathon1));


        userRepository.save(student1);
        userRepository.save(student2);
        userRepository.save(student3);
        userRepository.save(mentor1);
        userRepository.save(mentor2);
        marathon1.setUsers(List.of(student1, student2, mentor1));
        marathonRepository.save(marathon1);
    }

    @Test
    public void getAllStudentsTest() {
        String expected = List.of(student1, student2, student3).toString();
        String actual = userRepository.getAllByRole(User.Role.valueOf("TRAINEE")).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllByMarathonsIdAndRoleTest() {
        String expected = List.of(student1, student2).toString();
        String actual = userRepository.findAllByMarathonsIdAndRole(marathon1.getId(), User.Role.valueOf("TRAINEE")).toString();
        Assertions.assertEquals(expected, actual);
    }

}
