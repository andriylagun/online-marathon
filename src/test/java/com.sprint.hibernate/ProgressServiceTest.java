package com.sprint.hibernate;

import com.sprint.hibernate.entity.*;
import com.sprint.hibernate.repository.*;
import com.sprint.hibernate.service.ProgressService;
import com.sprint.hibernate.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProgressServiceTest {

    private final ProgressService progressService;

    @Autowired
    public ProgressServiceTest(ProgressService progressService) {
        this.progressService = progressService;
    }

    @MockBean
    private ProgressRepository progressRepository;

    @MockBean
    private MarathonRepository marathonRepository;

    @MockBean
    private SprintRepository sprintRepository;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private UserRepository userRepository;

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

    @BeforeEach
    public void setUp() {
        //Create marathons
        marathon1 = Marathon.builder().id(1).title("JOM_1").build();
        marathon2 = Marathon.builder().id(2).title("JOM_2").build();
//        marathonRepository.save(marathon1);
//        marathonRepository.save(marathon2);

        // Create sprints
        sprint1 = Sprint.builder().title("Sprint 1").marathon(marathon1).build();
        sprint2 = Sprint.builder().title("Sprint 2").marathon(marathon2).build();
//        sprintRepository.save(sprint1);
//        sprintRepository.save(sprint2);

        // Create tasks
        task1 = Task.builder().title("task1").sprint(sprint1).build();
        task2 = Task.builder().title("task2").sprint(sprint2).build();
//        taskRepository.save(task1);
//        taskRepository.save(task2);

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

//        userRepository.save(student1);
//        userRepository.save(student2);
//        userRepository.save(student3);
//        userRepository.save(mentor1);
//        userRepository.save(mentor2);

        // Create progress
        progress1 = Progress.builder().started(LocalDate.of(2020, 7, 25))
                .updated(LocalDate.of(2020, 7, 26))
                .status(Progress.TaskStatus.PENDING).trainee(mentor1).task(task1).build();
        progress2 = Progress.builder().started(LocalDate.of(2020, 7, 27))
                .updated(LocalDate.of(2020, 7, 28))
                .status(Progress.TaskStatus.PASS).trainee(mentor1).task(task2).build();
        progress3 = Progress.builder().started(LocalDate.of(2020, 7, 25))
                .updated(LocalDate.of(2020, 7, 26))
                .status(Progress.TaskStatus.PENDING).trainee(mentor1).task(task1).build();
//        progressRepository.save(progress1);
//        progressRepository.save(progress2);
//        progressRepository.save(progress3);


        Mockito.when(progressRepository.findById(progress1.getId()))
                .thenReturn(Optional.of(progress1));
//
//        Mockito.when(userRepository.getAllByRole(User.Role.valueOf("TRAINEE")))
//                .thenReturn(List.of(student1, student2, student3));
//
//        Mockito.when(userRepository.findAllByMarathonsIdAndRole(1L, User.Role.valueOf("TRAINEE")))
//                .thenReturn(List.of(student1, student2));
//
//        //??????????????????????????
//        Mockito.when(marathonRepository.findById(marathon1.getId()))
//                .thenReturn(Optional.of(marathon1));

    }

    @Test
    public void getProgressByIdTest() {
        String expected = progress1.toString();
        String actual = progressService.getProgressById(progress1.getId()).toString();
        Assert.assertEquals(expected, actual);
    }

    //Fail
    @Test
    public void addOrUpdateProgressTest() {
        Mockito.when(progressRepository.getOne(1L))
                .thenReturn(progress1);
        progressService.addOrUpdateProgress(progress1);
        verify(progressRepository, times(1)).save(progress1);
    }

    @Test
    public void addTaskForStudentTest() {

    }

    @Test
    public void setStatusTest() {

    }

    @Test
    public void allProgressByUserIdAndMarathonIdTest() {

    }

    @Test
    public void allProgressByUserIdAndSprintIdTest() {

    }

    @Test
    public void deleteProgressByIdTest() {

    }

    @Test
    public void deleteAllTest() {

    }


}