package com.sprint.hibernate;


import com.sprint.hibernate.entity.*;
import com.sprint.hibernate.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.List;


@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProgressRepositoryTest {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    MarathonRepository marathonRepository;

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
        marathonRepository.save(marathon1);
        marathonRepository.save(marathon2);

        // Create sprints
        sprint1 = Sprint.builder().title("Sprint 1").marathon(marathon1).build();
        sprint2 = Sprint.builder().title("Sprint 2").marathon(marathon2).build();
        sprintRepository.save(sprint1);
        sprintRepository.save(sprint2);

        // Create tasks
        task1 = Task.builder().title("task1").sprint(sprint1).build();
        task2 = Task.builder().title("task2").sprint(sprint2).build();
        taskRepository.save(task1);
        taskRepository.save(task2);

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

        userRepository.save(student1);
        userRepository.save(student2);
        userRepository.save(student3);
        userRepository.save(mentor1);
        userRepository.save(mentor2);

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
        progressRepository.save(progress1);
        progressRepository.save(progress2);
        progressRepository.save(progress3);

    }

    @Test
    public void findAllProgressByTraineeIdAndTaskSprintIdTest() {
        String expected = List.of(progress1, progress3).toString();
        String actual = progressRepository.findAllByTraineeIdAndTaskSprintId(mentor1.getId(), sprint1.getId()).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllByTraineeIdAndTaskSprintMarathonId() {
        String expected = List.of(progress2).toString();
        String actual = progressRepository.findAllByTraineeIdAndTaskSprintMarathonId(mentor1.getId(), marathon2.getId()).toString();
        Assertions.assertEquals(expected, actual);
    }

    //Fail
    @Test
    public void findAllProgressByTraineeIdTest() {
        String expected = List.of(progress1, progress2, progress3).toString();
        String actual = progressRepository.findAllByTraineeId(mentor1.getId()).toString();
        Assertions.assertEquals(expected, actual);
    }

}
