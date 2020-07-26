package com.sprint.hibernate;

import com.sprint.hibernate.entity.*;
import com.sprint.hibernate.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplicationTest2 {

    //    STUDENTS
    private final static long FIRST_STUDENT_ID = 1;
    private final static String FIRST_STUDENT_FIRST_NAME = "Anna";
    private final static String FIRST_STUDENT_LAST_NAME = "Ivanochko";
    private final static String FIRST_STUDENT_EMAIL = "anna@gmail.com";
    private final static String FIRST_STUDENT_PASSWORD = "abracadabra";
    private final static String FIRST_STUDENT_ROLE = "TRAINEE";

    private final static long SECOND_STUDENT_ID = 2;
    private final static String SECOND_STUDENT_FIRST_NAME = "Ivan";
    private final static String SECOND_STUDENT_LAST_NAME = "Dorn";
    private final static String SECOND_STUDENT_EMAIL = "ivan@gmail.com";
    private final static String SECOND_STUDENT_PASSWORD = "kolobok";
    private final static String SECOND_STUDENT_ROLE = "TRAINEE";

    private final static long THIRD_STUDENT_ID = 3;
    private final static String THIRD_STUDENT_FIRST_NAME = "Oksana";
    private final static String THIRD_STUDENT_LAST_NAME = "Boychuk";
    private final static String THIRD_STUDENT_EMAIL = "oksana@gmail.com";
    private final static String THIRD_STUDENT_PASSWORD = "556677";
    private final static String THIRD_STUDENT_ROLE = "TRAINEE";

    private final static long FOURTH_STUDENT_ID = 17;
    private final static String FOURTH_STUDENT_FIRST_NAME = "Olia";
    private final static String FOURTH_STUDENT_LAST_NAME = "Kobylianska";
    private final static String FOURTH_STUDENT_EMAIL = "olia@gmail.com";
    private final static String FOURTH_STUDENT_PASSWORD = "55ki54";
    private final static String FOURTH_STUDENT_ROLE = "TRAINEE";

    //    MENTORS
    private final static long FIRST_MENTOR_ID = 4;
    private final static String FIRST_MENTOR_FIRST_NAME = "Natalia";
    private final static String FIRST_MENTOR_LAST_NAME = "Romanenko";
    private final static String FIRST_MENTOR_EMAIL = "natalia@gmail.com";
    private final static String FIRST_MENTOR_PASSWORD = "nata567";
    private final static String FIRST_MENTOR_ROLE = "MENTOR";

    private final static long SECOND_MENTOR_ID = 5;
    private final static String SECOND_MENTOR_FIRST_NAME = "Mykola";
    private final static String SECOND_MENTOR_LAST_NAME = "Demchyna";
    private final static String SECOND_MENTOR_EMAIL = "mykola@gmail.com";
    private final static String SECOND_MENTOR_PASSWORD = "mykola25";
    private final static String SECOND_MENTOR_ROLE = "MENTOR";

    //   MARATHONS
    private final static long FIRST_MARATHON_ID = 6;
    private final static String FIRST_MARATHON_TITLE = "Java Online Marathon";

    private final static long SECOND_MARATHON_ID = 7;
    private final static String SECOND_MARATHON_TITLE = "C# Online Marathon";

    //   SPRINTS
    private final static long FIRST_SPRINT_ID = 1;
    private final static String FIRST_SPRINT_TITLE = "Spring Boot";
    private final static LocalDate FIRST_SPRINT_START_DATE = LocalDate.of(2020, 07, 20);
    private final static LocalDate FIRST_SPRINT_FINISH = LocalDate.of(2020, 07, 22);
    private final static long FIRST_SPRINT_MARATHON_ID = 1;

    private final static long SECOND_SPRINT_ID = 2;
    private final static String SECOND_SPRINT_TITLE = "Hibernate";
    private final static LocalDate SECOND_SPRINT_START_DATE = LocalDate.of(2020, 07, 23);
    private final static LocalDate SECOND_SPRINT_FINISH = LocalDate.of(2020, 07, 25);
    private final static long SECOND_SPRINT_MARATHON_ID = 1;

    private final static long THIRD_SPRINT_ID = 3;
    private final static String THIRD_SPRINT_TITLE = "Delegates";
    private final static LocalDate THIRD_SPRINT_START_DATE = LocalDate.of(2020, 07, 23);
    private final static LocalDate THIRD_SPRINT_FINISH = LocalDate.of(2020, 07, 25);
    private final static long THIRD_SPRINT_MARATHON_ID = 2;

    //   TASKS
    private final static long FIRST_TASK_ID = 1;
    private final static String FIRST_TASK_TITLE = "Task1";
    private final static LocalDate FIRST_TASK_CREATED = LocalDate.of(2020, 07, 20);
    private final static LocalDate FIRST_TASK_UPDATED = LocalDate.of(2020, 07, 21);

    private final static long SECOND_TASK_ID = 2;
    private final static String SECOND_TASK_TITLE = "Task2";
    private final static LocalDate SECOND_TASK_CREATED = LocalDate.of(2020, 07, 20);
    private final static LocalDate SECOND_TASK_UPDATED = LocalDate.of(2020, 07, 21);

    private final static long THIRD_TASK_ID = 3;
    private final static String THIRD_TASK_TITLE = "Task3";
    private final static LocalDate THIRD_TASK_CREATED = LocalDate.of(2020, 07, 23);
    private final static LocalDate THIRD_TASK_UPDATED = LocalDate.of(2020, 07, 24);

    private final static long FOURTH_TASK_ID = 4;
    private final static String FOURTH_TASK_TITLE = "Task4";
    private final static LocalDate FOURTH_TASK_CREATED = LocalDate.of(2020, 07, 23);
    private final static LocalDate FOURTH_TASK_UPDATED = LocalDate.of(2020, 07, 24);

    //PROGRESS
    private final static long FIRST_PROGRESS_ID = 1;
    private final static LocalDate FIRST_PROGRESS_STARTED = LocalDate.of(2020, 07, 20);
    private final static LocalDate FIRST_PROGRESS_UPDATED = LocalDate.of(2020, 07, 21);
    private final static String FIRST_PROGRESS_STATUS = "PASS";

    private final static long SECOND_PROGRESS_ID = 2;
    private final static LocalDate SECOND_PROGRESS_STARTED = LocalDate.of(2020, 07, 20);
    private final static LocalDate SECOND_PROGRESS_UPDATED = LocalDate.of(2020, 07, 22);
    private final static String SECOND_PROGRESS_STATUS = "PENDING";


    @Autowired
    private MarathonService marathonService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private SprintService sprintService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Autowired
    public ApplicationTest2(MarathonService marathonService, ProgressService progressService,
                           SprintService sprintService, TaskService taskService, UserService userService) {
        this.marathonService = marathonService;
        this.progressService = progressService;
        this.sprintService = sprintService;
        this.taskService = taskService;
        this.userService = userService;
        try {
            setUp();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

//    @BeforeAll
    private void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Create students
        User student1 = new User();
        student1.setId(FIRST_STUDENT_ID);
        student1.setFirstName(FIRST_STUDENT_FIRST_NAME);
        student1.setLastName(FIRST_STUDENT_LAST_NAME);
        student1.setEmail(FIRST_STUDENT_EMAIL);
        student1.setPassword(FIRST_STUDENT_PASSWORD);
        student1.setRole(User.Role.valueOf(FIRST_STUDENT_ROLE));
        userService.createOrUpdateUser(student1);
        User student2 = new User();
        student2.setFirstName(SECOND_STUDENT_FIRST_NAME);
        student2.setLastName(SECOND_STUDENT_LAST_NAME);
        student2.setEmail(SECOND_STUDENT_EMAIL);
        student2.setPassword(SECOND_STUDENT_PASSWORD);
        student2.setRole(User.Role.valueOf(SECOND_STUDENT_ROLE));
        userService.createOrUpdateUser(student2);
        User student3 = new User();
        student3.setFirstName(THIRD_STUDENT_FIRST_NAME);
        student3.setLastName(THIRD_STUDENT_LAST_NAME);
        student3.setEmail(THIRD_STUDENT_EMAIL);
        student3.setPassword(THIRD_STUDENT_PASSWORD);
        student3.setRole(User.Role.valueOf(THIRD_STUDENT_ROLE));
        userService.createOrUpdateUser(student3);
        // Create mentors
        User mentor1 = new User();
        mentor1.setFirstName(FIRST_MENTOR_FIRST_NAME);
        mentor1.setLastName(FIRST_MENTOR_LAST_NAME);
        mentor1.setEmail(FIRST_MENTOR_EMAIL);
        mentor1.setPassword(FIRST_MENTOR_PASSWORD);
        mentor1.setRole(User.Role.valueOf(FIRST_MENTOR_ROLE));
        userService.createOrUpdateUser(mentor1);
        User mentor2 = new User();
        mentor2.setFirstName(SECOND_MENTOR_FIRST_NAME);
        mentor2.setLastName(SECOND_MENTOR_LAST_NAME);
        mentor2.setEmail(SECOND_MENTOR_EMAIL);
        mentor2.setPassword(SECOND_MENTOR_PASSWORD);
        mentor2.setRole(User.Role.valueOf(SECOND_MENTOR_ROLE));
        userService.createOrUpdateUser(mentor2);

        // Create marathon
        Marathon marathon1 = new Marathon();
        marathon1.setTitle(FIRST_MARATHON_TITLE);
        marathonService.createOrUpdate(marathon1);

        Marathon marathon2 = new Marathon();
        marathon2.setTitle(SECOND_MARATHON_TITLE);
        marathonService.createOrUpdate(marathon2);

        // Create sprints
        Sprint sprint1 = new Sprint();
        sprint1.setTitle(FIRST_SPRINT_TITLE);
        sprint1.setStartDate(FIRST_SPRINT_START_DATE);
        sprint1.setFinish(FIRST_SPRINT_FINISH);
        sprint1.setMarathon(marathon1);
        sprintService.createOrUpdateSprint(sprint1);

        Sprint sprint2 = new Sprint();
        sprint2.setTitle(SECOND_SPRINT_TITLE);
        sprint2.setStartDate(SECOND_SPRINT_START_DATE);
        sprint2.setFinish(SECOND_SPRINT_FINISH);
        sprint2.setMarathon(marathon1);
        sprintService.createOrUpdateSprint(sprint2);

        Sprint sprint3 = new Sprint();
        sprint3.setTitle(THIRD_SPRINT_TITLE);
        sprint3.setStartDate(THIRD_SPRINT_START_DATE);
        sprint3.setFinish(THIRD_SPRINT_FINISH);
        sprint3.setMarathon(marathon2);
        sprintService.createOrUpdateSprint(sprint3);

        // Create tasks
        Task task1 = new Task();
        task1.setTitle(FIRST_TASK_TITLE);
        task1.setCreated(FIRST_TASK_CREATED);
        task1.setUpdated(FIRST_TASK_UPDATED);
        taskService.createOrUpdateTask(task1);

        Task task2 = new Task();
        task2.setTitle(SECOND_TASK_TITLE);
        task2.setCreated(SECOND_TASK_CREATED);
        task2.setUpdated(SECOND_TASK_UPDATED);
        taskService.createOrUpdateTask(task2);

        Task task3 = new Task();
        task3.setTitle(THIRD_TASK_TITLE);
        task3.setCreated(THIRD_TASK_CREATED);
        task3.setUpdated(THIRD_TASK_UPDATED);
        taskService.createOrUpdateTask(task3);

        Task task4 = new Task();
        task4.setTitle(FOURTH_TASK_TITLE);
        task4.setCreated(FOURTH_TASK_CREATED);
        task4.setUpdated(FOURTH_TASK_UPDATED);
        taskService.createOrUpdateTask(task4);

        // Create progress
        Progress progress1 = new Progress();
        progress1.setStarted(FIRST_PROGRESS_STARTED);
        progress1.setUpdated(FIRST_PROGRESS_UPDATED);
        progress1.setStatus(Progress.TaskStatus.valueOf(FIRST_PROGRESS_STATUS));
        progress1.setTrainee(mentor1);
        progressService.addOrUpdateProgress(progress1);

        Progress progress2 = new Progress();
        progress2.setStarted(SECOND_PROGRESS_STARTED);
        progress2.setUpdated(SECOND_PROGRESS_UPDATED);
        progress2.setStatus(Progress.TaskStatus.valueOf(SECOND_PROGRESS_STATUS));
        progress2.setTrainee(mentor1);
        progressService.addOrUpdateProgress(progress2);

    }

    //==================Testing UserService Methods=============
    @Test
    public void checkGetUserById() {
        String expectedUser="User(" +
                "id=1" +
                ", firstName=Anna" +
                ", lastName=Ivanochko" +
                ", email=anna@gmail.com" +
                ", password=abracadabra" +
                ", role=TRAINEE"  +
                ")";
        String actualUser = userService.getUserById(1).toString();
        Assertions.assertEquals(expectedUser, actualUser, "checkGetUserById()");
    }

     @Test
     public void checkGetAll() {
         List<User> students = getInitialStudents();
         List<User> mentors = getInitialMentors();
         List<User> expected = new ArrayList<>();
         expected.addAll(students);
         expected.addAll(mentors);
         List<User> actual = userService.getAll();
         Assertions.assertEquals(expected.toString(), actual.toString(), "checkGetAll()");
    }

    @Test
    public void checkCreateOrUpdateUserShouldCreate() {
        List<User> students = getInitialStudents();
        List<User> mentors = getInitialMentors();
        List<User> expected = new ArrayList<>();
        expected.addAll(students);
        expected.addAll(mentors);
        User student4 = new User();
        student4.setId(FOURTH_STUDENT_ID);
        student4.setFirstName(FOURTH_STUDENT_FIRST_NAME);
        student4.setLastName(FOURTH_STUDENT_LAST_NAME);
        student4.setEmail(FOURTH_STUDENT_EMAIL);
        student4.setPassword(FOURTH_STUDENT_PASSWORD);
        student4.setRole(User.Role.valueOf(FOURTH_STUDENT_ROLE));
        expected.add(student4);
        try {
            userService.createOrUpdateUser(student4);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        List<User> actual = userService.getAll();
        Assertions.assertEquals(expected.toString(), actual.toString(), "checkCreateOrUpdateUserShouldCreate()");
    }

    @Test
    public void checkCreateOrUpdateUserShouldUpdate() {
        List<User> students = getInitialStudents();
        List<User> mentors = getInitialMentors();
        List<User> expected = new ArrayList<>();
        expected.addAll(students);
        expected.addAll(mentors);
        User student4 = new User();
        student4.setId(THIRD_STUDENT_ID);
        student4.setFirstName(FOURTH_STUDENT_FIRST_NAME);
        student4.setLastName(FOURTH_STUDENT_LAST_NAME);
        student4.setEmail(FOURTH_STUDENT_EMAIL);
        student4.setPassword(FOURTH_STUDENT_PASSWORD);
        student4.setRole(User.Role.valueOf(FOURTH_STUDENT_ROLE));
        expected.remove(2);
        expected.add(student4);
        try {
            userService.createOrUpdateUser(student4);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        List<User> actual = userService.getAll();
        Assertions.assertEquals(expected.toString(), actual.toString(), "checkCreateOrUpdateUserShouldUpdate()");
    }

    @Test
    public void checkGetAllByRole() {
        List<User> expected = getInitialStudents();

        List<User> actual = userService.getAllByRole(FIRST_STUDENT_ROLE);
        Assertions.assertEquals(expected.toString(), actual.toString(), "checkGetAllByRole");
    }

    //==================Testing MarathonService Methods=============
    @Test
    public void checkGetAllMarathons() {
        List<Marathon> expected = getInitialMarathons();
        List<Marathon> actual = marathonService.getAll();
        Assertions.assertEquals(expected.toString(), actual.toString(), "checkGetAllMarathons()");
    }

//    @Test
//    public void checkGetMarathonById() {
//        Marathon expected = new Marathon();
//        expected.setId(FIRST_MARATHON_ID);
//        expected.setTitle(FIRST_MARATHON_TITLE);
//        Marathon actual = marathonService.getMarathonById(FIRST_MARATHON_ID);
//        Assertions.assertEquals(expected.toString(), actual.toString(), "checkGetMarathonById()");
//    }

//    @Test
//    public void checkCreateOrUpdateMarathonShouldCreate() {
//        List<Marathon> expected = getInitialMarathons();
//        Marathon newMarathon = new Marathon();
//        newMarathon.setId(FIRST_MARATHON_ID);
//        newMarathon.setTitle(FIRST_MARATHON_TITLE);
//        expected.add(newMarathon);
//        User student4 = new User();
//        student4.setId(FOURTH_STUDENT_ID);
//        student4.setFirstName(FOURTH_STUDENT_FIRST_NAME);
//        student4.setLastName(FOURTH_STUDENT_LAST_NAME);
//        student4.setEmail(FOURTH_STUDENT_EMAIL);
//        student4.setPassword(FOURTH_STUDENT_PASSWORD);
//        student4.setRole(User.Role.valueOf(FOURTH_STUDENT_ROLE));
//        expected.add(student4);
//        try {
//            userService.createOrUpdateUser(student4);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        List<User> actual = userService.getAll();
//        Assertions.assertEquals(expected.toString(), actual.toString(), "checkCreateOrUpdateMarathonShouldCreate()");
//    }

    //==================Testing SprintService Methods=============

//    @Test
//    public void checkGetSprintById() {
//        Sprint expected = new Sprint();
//        expected.setTitle(FIRST_SPRINT_TITLE);
//        expected.setStartDate(FIRST_SPRINT_START_DATE);
//        expected.setFinish(FIRST_SPRINT_FINISH);
//        expected.setMarathon(marathon1);
//
//        expected.setId(FIRST_MARATHON_ID);
//        expected.setTitle(FIRST_MARATHON_TITLE);
//
//        Sprint actual = sprintService.getSprintById(FIRST_SPRINT_ID);
//        Assertions.assertEquals(expected.toString(), actual.toString(), "checkGetSprintById()");
//    }




    //============Helping methods=============
    private List<User> getInitialStudents() {
        List<User> initial = new ArrayList<>();
        User student1 = new User();
        student1.setId(FIRST_STUDENT_ID);
        student1.setFirstName(FIRST_STUDENT_FIRST_NAME);
        student1.setLastName(FIRST_STUDENT_LAST_NAME);
        student1.setEmail(FIRST_STUDENT_EMAIL);
        student1.setPassword(FIRST_STUDENT_PASSWORD);
        student1.setRole(User.Role.valueOf(FIRST_STUDENT_ROLE));
        initial.add(student1);
        User student2 = new User();
        student2.setId(SECOND_STUDENT_ID);
        student2.setFirstName(SECOND_STUDENT_FIRST_NAME);
        student2.setLastName(SECOND_STUDENT_LAST_NAME);
        student2.setEmail(SECOND_STUDENT_EMAIL);
        student2.setPassword(SECOND_STUDENT_PASSWORD);
        student2.setRole(User.Role.valueOf(SECOND_STUDENT_ROLE));
        initial.add(student2);
        User student3 = new User();
        student3.setId(THIRD_STUDENT_ID);
        student3.setFirstName(THIRD_STUDENT_FIRST_NAME);
        student3.setLastName(THIRD_STUDENT_LAST_NAME);
        student3.setEmail(THIRD_STUDENT_EMAIL);
        student3.setPassword(THIRD_STUDENT_PASSWORD);
        student3.setRole(User.Role.valueOf(THIRD_STUDENT_ROLE));
        initial.add(student3);
        return initial;
    }

    private List<User> getInitialMentors() {
        List<User> initial = new ArrayList<>();
        User mentor1 = new User();
        mentor1.setId(FIRST_MENTOR_ID);
        mentor1.setFirstName(FIRST_MENTOR_FIRST_NAME);
        mentor1.setLastName(FIRST_MENTOR_LAST_NAME);
        mentor1.setEmail(FIRST_MENTOR_EMAIL);
        mentor1.setPassword(FIRST_MENTOR_PASSWORD);
        mentor1.setRole(User.Role.valueOf(FIRST_MENTOR_ROLE));
        initial.add(mentor1);
        User mentor2 = new User();
        mentor2.setId(SECOND_MENTOR_ID);
        mentor2.setFirstName(SECOND_MENTOR_FIRST_NAME);
        mentor2.setLastName(SECOND_MENTOR_LAST_NAME);
        mentor2.setEmail(SECOND_MENTOR_EMAIL);
        mentor2.setPassword(SECOND_MENTOR_PASSWORD);
        mentor2.setRole(User.Role.valueOf(SECOND_MENTOR_ROLE));
        initial.add(mentor2);
        return initial;
    }

    private List<Marathon> getInitialMarathons() {
        List<Marathon> initial = new ArrayList<>();
        Marathon marathon1 = new Marathon();
        marathon1.setId(FIRST_MARATHON_ID);
        marathon1.setTitle(FIRST_MARATHON_TITLE);
        initial.add(marathon1);
        Marathon marathon2 = new Marathon();
        marathon2.setId(SECOND_MARATHON_ID);
        marathon2.setTitle(SECOND_MARATHON_TITLE);
        initial.add(marathon2);
        return initial;
    }

//    public void deleteUsers() {
//        List<User> existingUsers = userService.getAll();
//        for (int i = 0; i < existingUsers.size(); i++) {
//            userService.deleteUserById(existingUsers.get(i).getId());
//        }
//    }

}




























//package com.sprint.hibernate;
//
//import com.sprint.hibernate.entity.User;
//import com.sprint.hibernate.service.*;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDate;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class ApplicationTest2 {
//
//    //    STUDENTS
//    private final static long FIRST_STUDENT_ID = 1;
//    private final static String FIRST_STUDENT_FIRST_NAME = "Anna";
//    private final static String FIRST_STUDENT_LAST_NAME = "Karenina";
//    private final static String FIRST_STUDENT_EMAIL = "anna@jmail.com";
//    private final static String FIRST_STUDENT_PASSWORD = "abracadabra";
//    private final static String FIRST_STUDENT_ROLE = "TRAINEE";
//
//    private final static long SECOND_STUDENT_ID = 2;
//    private final static String SECOND_STUDENT_FIRST_NAME = "Ivan";
//    private final static String SECOND_STUDENT_LAST_NAME = "Dorn";
//    private final static String SECOND_STUDENT_EMAIL = "ivan@gmail.com";
//    private final static String SECOND_STUDENT_PASSWORD = "kolobok";
//    private final static String SECOND_STUDENT_ROLE = "TRAINEE";
//
//    private final static long THIRD_STUDENT_ID = 3;
//    private final static String THIRD_STUDENT_FIRST_NAME = "Oksana";
//    private final static String THIRD_STUDENT_LAST_NAME = "Boychuk";
//    private final static String THIRD_STUDENT_EMAIL = "oksana@gmail.com";
//    private final static String THIRD_STUDENT_PASSWORD = "556677";
//    private final static String THIRD_STUDENT_ROLE = "TRAINEE";
//
//    //    MENTORS
//    private final static long FIRST_MENTOR_ID = 1;
//    private final static String FIRST_MENTOR_FIRST_NAME = "Natalia";
//    private final static String FIRST_MENTOR_LAST_NAME = "Romanenko";
//    private final static String FIRST_MENTOR_EMAIL = "natalia@gmail.com";
//    private final static String FIRST_MENTOR_PASSWORD = "nata567";
//    private final static String FIRST_MENTOR_ROLE = "MENTOR";
//
//    private final static long SECOND_MENTOR_ID = 2;
//    private final static String SECOND_MENTOR_FIRST_NAME = "Mykola";
//    private final static String SECOND_MENTOR_LAST_NAME = "Demchyna";
//    private final static String SECOND_MENTOR_EMAIL = "mykola@gmail.com";
//    private final static String SECOND_MENTOR_PASSWORD = "mykola25";
//    private final static String SECOND_MENTOR_ROLE = "MENTOR";
//
//    //   MARATHONS
//    private final static long FIRST_MARATHON_ID = 1;
//    private final static String FIRST_MARATHON_TITLE = "Java Online Marathon";
//
//    //   SPRINTS
//    private final static long FIRST_SPRINT_ID = 1;
//    private final static String FIRST_SPRINT_TITLE = "Spring";
//    private final static LocalDate FIRST_SPRINT_START_DATE = LocalDate.of(2020, 07, 20);
//    private final static LocalDate FIRST_SPRINT_FINISH = LocalDate.of(2020, 07, 22);
//    private final static long FIRST_SPRINT_MARATHON_ID = 1;
//
//    private final static long SECOND_SPRINT_ID = 2;
//    private final static String SECOND_SPRINT_TITLE = "Hibernate";
//    private final static LocalDate SECOND_SPRINT_START_DATE = LocalDate.of(2020, 07, 23);
//    private final static LocalDate SECOND_SPRINT_FINISH = LocalDate.of(2020, 07, 25);
//    private final static long SECOND_SPRINT_MARATHON_ID = 1;
//
//    //   TASKS
//
//
//    //PROGRESS
//
//
//    private MarathonService marathonService;
//    private ProgressService progressService;
//    private SprintService sprintService;
//    private TaskService taskService;
//    private UserService userService;
//
//    @Autowired
//    public ApplicationTest2(MarathonService marathonService, ProgressService progressService,
//                            SprintService sprintService, TaskService taskService, UserService userService) {
//        this.marathonService = marathonService;
//        this.progressService = progressService;
//        this.sprintService = sprintService;
//        this.taskService = taskService;
//        this.userService = userService;
//        try {
//            setUp();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
////    @BeforeAll
//    private void setUp() throws NoSuchFieldException, IllegalAccessException {
//        // Create students
//        User student1 = new User();
////        student1.setId(FIRST_STUDENT_ID);
//        student1.setFirstName(FIRST_STUDENT_FIRST_NAME);
//        student1.setLastName(FIRST_STUDENT_LAST_NAME);
//        student1.setEmail(FIRST_STUDENT_EMAIL);
//        student1.setPassword(FIRST_STUDENT_PASSWORD);
//        student1.setRole(User.Role.valueOf(FIRST_STUDENT_ROLE));
//        User student2 = new User();
//        student2.setFirstName(SECOND_STUDENT_FIRST_NAME);
//        student2.setLastName(SECOND_STUDENT_LAST_NAME);
//        student2.setEmail(SECOND_STUDENT_EMAIL);
//        student2.setPassword(SECOND_STUDENT_PASSWORD);
//        student2.setRole(User.Role.valueOf(SECOND_STUDENT_ROLE));
//        User student3 = new User();
//        student3.setFirstName(THIRD_STUDENT_FIRST_NAME);
//        student3.setLastName(THIRD_STUDENT_LAST_NAME);
//        student3.setEmail(THIRD_STUDENT_EMAIL);
//        student3.setPassword(THIRD_STUDENT_PASSWORD);
//        student3.setRole(User.Role.valueOf(THIRD_STUDENT_ROLE));
//
//        // Create mentors
//
//        // Create marathon
//
//        // Create sprints
//
//        // Create tasks
//
//        // Create progress
//
//
//    }
//
//    @Test
//    public void checkGetUserById() throws NoSuchFieldException, IllegalAccessException {
//        String expectedUser="User[" +
//                "id=1" +
//                ", firstName=Anna'" +'\'' +
//                ", lastName='Ivanochko"+ '\'' +
//                ", email='anna@gmail.com"+ '\'' +
//                ", password='abracadabra"+ '\'' +
//                ", role=TRAINEE"  +
//                ']';
//
//        String actualUser = userService.getUserById(FIRST_STUDENT_ID).toString();
//        Assertions.assertEquals(expectedUser, actualUser, "checkGetUserById()");
//    }
//
//   // @Test
//    //public void checkGetAll() {
//       // List<User> expected = List.of(
////
//  //              );
//    //}
//
//}
