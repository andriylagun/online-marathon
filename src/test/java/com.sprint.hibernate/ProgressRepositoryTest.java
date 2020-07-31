package com.sprint.hibernate;


import com.sprint.hibernate.entity.*;
import com.sprint.hibernate.repository.ProgressRepository;
import com.sprint.hibernate.repository.TaskRepository;
import com.sprint.hibernate.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;


@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProgressRepositoryTest {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    private static User student1;
    private static User student2;
    private static User student3;
    private static User mentor1;
    private static User mentor2;
    private static Marathon marathon1;
    private static Marathon marathon2;
    private static Sprint sprint1;
    private static Sprint sprint2;
    private static Task task1;
    private static Task task2;
    private static Progress progress1;
    private static Progress progress2;
    private static Progress progress3;

    @BeforeAll
    public void setUp() {
        //Create marathons
        marathon1 = Marathon.builder().id(1).title("JOM_1").build();
        marathon2 = Marathon.builder().id(2).title("JOM_2").build();

        // Create students
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
        // Create mentors
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
        // Create sprints
        sprint1 = Sprint.builder()
                .title("Sprint 1")
                .build();
        sprint2 = Sprint.builder()
                .title("Sprint 2")
                .build();
        // Create tasks
        task1 = Task.builder().title("task1").build();
        task2 = Task.builder().title("task2").build();

        // Create progress
        progress1 = Progress.builder().started(LocalDate.of(2020, 7, 25))
                .updated(LocalDate.of(2020, 7, 26))
                .status(Progress.TaskStatus.PENDING).build();
        progress2 = Progress.builder().started(LocalDate.of(2020, 7, 27))
                .updated(LocalDate.of(2020, 7, 28))
                .status(Progress.TaskStatus.PASS).build();

        userRepository.save(student1);
        userRepository.save(student2);
        userRepository.save(student3);
        userRepository.save(mentor1);
        userRepository.save(mentor2);

        taskRepository.save(task1);
    }

    @Test
    public void addProgressTest() {
        Progress progress3 = Progress.builder().started(LocalDate.of(2020, 7, 29))
                .updated(LocalDate.of(2020, 7, 30))
                .status(Progress.TaskStatus.FAIL).trainee(student1).task(task1).build();
//        Progress progress3 = Progress.builder().started(LocalDate.of(2020, 7, 29))
//                .updated(LocalDate.of(2020, 7, 30))
//                .status(Progress.TaskStatus.FAIL).trainee(userRepository.findUserByEmail("petro@gmail.com")).task(task1).build();
        progressRepository.save(progress3);
        String expected = progress3.toString();

        String actual = progressRepository.getOne(progress3.getId()).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void deleteProgressTest() {

    }

    @Test
    public void findAllProgressByTraineeIdTest() {

    }

    @Test
    public void findAllProgressByTraineeIdAndTaskSprintIdTest() {

    }

    @Test
    public void findAllByTraineeIdAndTaskSprintMarathonId() {

    }

}
