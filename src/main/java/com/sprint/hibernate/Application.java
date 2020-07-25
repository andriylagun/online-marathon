package com.sprint.hibernate;

import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.math.BigInteger;


@SpringBootApplication
@RestController
public class Application {
    private MarathonService marathonService;
    private SprintService sprintService;
    private UserService userService;
    private TaskService taskService;
    private ProgressService progressService;

    //    STUDENTS
    private final static BigInteger FIRST_STUDENT_ID = BigInteger.valueOf(1);
    private final static String FIRST_STUDENT_FIRST_NAME = "Anna";
    private final static String FIRST_STUDENT_LAST_NAME = "Ivanochko";
    private final static String FIRST_STUDENT_EMAIL = "anna@gmail.com";
    private final static String FIRST_STUDENT_PASSWORD = "abracadabra";
    private final static String FIRST_STUDENT_ROLE = "TRAINEE";

    private final static BigInteger SECOND_STUDENT_ID = BigInteger.valueOf(2);
    private final static String SECOND_STUDENT_FIRST_NAME = "Ivan";
    private final static String SECOND_STUDENT_LAST_NAME = "Dorn";
    private final static String SECOND_STUDENT_EMAIL = "ivan@gmail.com";
    private final static String SECOND_STUDENT_PASSWORD = "kolobok";
    private final static String SECOND_STUDENT_ROLE = "TRAINEE";

    private final static BigInteger THIRD_STUDENT_ID = BigInteger.valueOf(3);
    private final static String THIRD_STUDENT_FIRST_NAME = "Oksana";
    private final static String THIRD_STUDENT_LAST_NAME = "Boychuk";
    private final static String THIRD_STUDENT_EMAIL = "oksana@gmail.com";
    private final static String THIRD_STUDENT_PASSWORD = "556677";
    private final static String THIRD_STUDENT_ROLE = "TRAINEE";

    @Autowired
    public Application(MarathonService marathonService, SprintService sprintService,
            UserService userService, TaskService taskService, ProgressService progressService) {
        this.marathonService = marathonService;
        this.sprintService = sprintService;
        this.userService = userService;
        this.taskService = taskService;
        this.progressService = progressService;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //@Override
    public void run(String... args) throws Exception {
        System.out.println("Application is running");
        // Create students
        try {
            User student1 = new User();
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

        } catch (ConstraintViolationException e) {
            System.out.println(e.getMessage());
        }

    }

//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }
}