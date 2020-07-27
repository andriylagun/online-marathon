package com.sprint.hibernate;

import com.sprint.hibernate.entity.*;
import com.sprint.hibernate.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationTest {

    private final MarathonService marathonService;
    private final ProgressService progressService;
    private final SprintService sprintService;
    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public ApplicationTest(MarathonService marathonService, ProgressService progressService,
                           SprintService sprintService, TaskService taskService, UserService userService) throws NoSuchFieldException, IllegalAccessException {
        this.marathonService = marathonService;
        this.progressService = progressService;
        this.sprintService = sprintService;
        this.taskService = taskService;
        this.userService = userService;
    }

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
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
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
        userService.createOrUpdateUser(student1);
        userService.createOrUpdateUser(student2);
        userService.createOrUpdateUser(student3);
        userService.createOrUpdateUser(mentor1);
        userService.createOrUpdateUser(mentor2);
        marathonService.createOrUpdate(marathon1);
        marathonService.createOrUpdate(marathon2);
        userService.addUserToMarathon(student1, marathon1);
        userService.addUserToMarathon(student2, marathon1);
        userService.addUserToMarathon(mentor1, marathon1);
        userService.addUserToMarathon(student1, marathon1);
        userService.addUserToMarathon(mentor1, marathon1);
        // Create sprints
        sprint1 = Sprint.builder()
                .title("Sprint 1")
                .build();
        sprint2 = Sprint.builder()
                .title("Sprint 2")
                .build();
        sprintService.addSprintToMarathon(sprint1, marathon1);
        sprintService.addSprintToMarathon(sprint2, marathon2);
        // Create tasks
        task1 = Task.builder().title("task1").build();
        task2 = Task.builder().title("task2").build();
        taskService.addTaskToSprint(task1, sprint1);
        taskService.addTaskToSprint(task2, sprint1);
        // Create progress

        progress1 = progressService.addTaskForStudent(task1, student1);
        progress2 = progressService.addTaskForStudent(task2, student2);
        progress3 = progressService.addTaskForStudent(task2, student1);
    }

    @Test
    public void checkGetUserById() {


        String expectedUser = "User(id=1, firstName=Uliana, lastName=Tomyn, email=uliana@gmail.com, password=password1, role=TRAINEE)";
        String actualUser = userService.getUserById(1).toString();
        Assertions.assertEquals(expectedUser, actualUser, "checkGetUserById()");

    }

    @Test
    public void checkGetMarathonById() {
        String expectedMarathon = "Marathon(id=1, title=JOM_1)";
        String actualMarathon = marathonService.getMarathonById(1).toString();
        Assertions.assertEquals(expectedMarathon, actualMarathon);
    }

    @Test
    public void checkGetUsersByRole() {

        String expected = List.of(mentor1, mentor2).toString();
        String actual = userService.getAllByRole("mentor").toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkUpdateUser() throws NoSuchFieldException, IllegalAccessException {
        String expectedUser = "User(id=1, firstName=Uliana, lastName=Tomyn, email=uliana@i.ua, password=password1, role=TRAINEE)";
        User updated = User.builder().
                id(1).
                firstName("Uliana").
                lastName("Tomyn").
                email("uliana@i.ua").
                password("password1")
                .role(User.Role.TRAINEE).build();
        userService.createOrUpdateUser(updated);
        String actualUser = userService.getUserById(1).toString();
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void checkGetAllUsers() {
        String expected = List.of(student1, student2, student3, mentor1, mentor2).toString();
        String actual = userService.getAll().toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkGetTaskById() {
        String expected = task1.toString();
        String actual = taskService.getTaskById(1).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkUpdateTask() {
        Task updated = Task.builder().id(1).created(LocalDate.now()).updated(LocalDate.now()).title("task1").sprint(sprint1).build();
        taskService.createOrUpdateTask(updated);
        String actual = taskService.getTaskById(1).toString();
        Assertions.assertEquals(updated.toString(), actual);
    }

    @Test
    public void checkSetStatus() {
        progressService.setStatus(Progress.TaskStatus.PENDING, progress3);
        String expected = "PENDING";
        String actual = progress3.getStatus().toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkAllProgressByUserIdAndSprintId() {
        String expected = List.of(
                progressService.getProgressById(2)
        ).toString();

        String actual = progressService.allProgressByUserIdAndSprintId(2, 1).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkAllProgressByUserIdAndMarathonId(){
        String expected=List.of(
                progressService.getProgressById(1),
                progressService.getProgressById(3)
        ).toString();
        String actual=progressService.allProgressByUserIdAndMarathonId(1,1).toString();
        Assertions.assertEquals(expected,actual);
    }
}
