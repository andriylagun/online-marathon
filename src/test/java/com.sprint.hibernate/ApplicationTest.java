package com.sprint.hibernate;

import com.sprint.hibernate.entity.User;
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
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplicationTest {

    //    STUDENTS
    private final static long FIRST_STUDENT_ID = 1;
    private final static String FIRST_STUDENT_FIRST_NAME = "Anna";
    private final static String FIRST_STUDENT_LAST_NAME = "Karenina";
    private final static String FIRST_STUDENT_EMAIL = "anna@jmail.com";
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

    //    MENTORS
    private final static long FIRST_MENTOR_ID = 1;
    private final static String FIRST_MENTOR_FIRST_NAME = "Natalia";
    private final static String FIRST_MENTOR_LAST_NAME = "Romanenko";
    private final static String FIRST_MENTOR_EMAIL = "natalia@gmail.com";
    private final static String FIRST_MENTOR_PASSWORD = "nata567";
    private final static String FIRST_MENTOR_ROLE = "MENTOR";

    private final static long SECOND_MENTOR_ID = 2;
    private final static String SECOND_MENTOR_FIRST_NAME = "Mykola";
    private final static String SECOND_MENTOR_LAST_NAME = "Demchyna";
    private final static String SECOND_MENTOR_EMAIL = "mykola@gmail.com";
    private final static String SECOND_MENTOR_PASSWORD = "mykola25";
    private final static String SECOND_MENTOR_ROLE = "MENTOR";

    //   MARATHONS
    private final static long FIRST_MARATHON_ID = 1;
    private final static String FIRST_MARATHON_TITLE = "Java Online Marathon";

    //   SPRINTS
    private final static long FIRST_SPRINT_ID = 1;
    private final static String FIRST_SPRINT_TITLE = "Spring";
    private final static LocalDate FIRST_SPRINT_START_DATE = LocalDate.of(2020, 07, 20);
    private final static LocalDate FIRST_SPRINT_FINISH = LocalDate.of(2020, 07, 22);
    private final static long FIRST_SPRINT_MARATHON_ID = 1;

    private final static long SECOND_SPRINT_ID = 2;
    private final static String SECOND_SPRINT_TITLE = "Hibernate";
    private final static LocalDate SECOND_SPRINT_START_DATE = LocalDate.of(2020, 07, 23);
    private final static LocalDate SECOND_SPRINT_FINISH = LocalDate.of(2020, 07, 25);
    private final static long SECOND_SPRINT_MARATHON_ID = 1;

    //   TASKS


    //PROGRESS


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
    public ApplicationTest(MarathonService marathonService, ProgressService progressService,
                           SprintService sprintService, TaskService taskService, UserService userService) {
        this.marathonService = marathonService;
        this.progressService = progressService;
        this.sprintService = sprintService;
        this.taskService = taskService;
        this.userService = userService;

    }
    @BeforeAll
    private static void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Create students
        User student1 = new User();
        student1.setId(FIRST_STUDENT_ID);
        student1.setFirstName(FIRST_STUDENT_FIRST_NAME);
        student1.setLastName(FIRST_STUDENT_LAST_NAME);
        student1.setEmail(FIRST_STUDENT_EMAIL);
        student1.setPassword(FIRST_STUDENT_PASSWORD);
        student1.setRole(User.Role.valueOf(FIRST_STUDENT_ROLE));
        User student2 = new User();
        student2.setFirstName(SECOND_STUDENT_FIRST_NAME);
        student2.setLastName(SECOND_STUDENT_LAST_NAME);
        student2.setEmail(SECOND_STUDENT_EMAIL);
        student2.setPassword(SECOND_STUDENT_PASSWORD);
        student2.setRole(User.Role.valueOf(SECOND_STUDENT_ROLE));
        User student3 = new User();
        student3.setFirstName(THIRD_STUDENT_FIRST_NAME);
        student3.setLastName(THIRD_STUDENT_LAST_NAME);
        student3.setEmail(THIRD_STUDENT_EMAIL);
        student3.setPassword(THIRD_STUDENT_PASSWORD);
        student3.setRole(User.Role.valueOf(THIRD_STUDENT_ROLE));

        // Create mentors

        // Create marathon

        // Create sprints

        // Create tasks

        // Create progress


    }

    @Test
    public void checkGetUserById() throws NoSuchFieldException, IllegalAccessException {
        String expectedUser="User[" +
                "id=1" +
                ", firstName=Anna'" +'\'' +
                ", lastName='Ivanochko"+ '\'' +
                ", email='anna@gmail.com"+ '\'' +
                ", password='abracadabra"+ '\'' +
                ", role=TRAINEE"  +
                ']';
        User student1 = new User();
        student1.setId(1);
        student1.setFirstName(FIRST_STUDENT_FIRST_NAME);
        student1.setLastName(FIRST_STUDENT_LAST_NAME);
        student1.setEmail(FIRST_STUDENT_EMAIL);
        student1.setPassword(FIRST_STUDENT_PASSWORD);
        student1.setRole(User.Role.valueOf(FIRST_STUDENT_ROLE));
        userService.createOrUpdateUser(student1);
        String actualUser = userService.getUserById(1).toString();
        Assertions.assertEquals(expectedUser, actualUser, "checkGetUserById()");
    }

   // @Test
    //public void checkGetAll() {
       // List<User> expected = List.of(
//
  //              );
    //}

}
