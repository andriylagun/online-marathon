package com.sprint.hibernate;

import com.sprint.hibernate.entity.*;
import com.sprint.hibernate.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.validation.ConstraintViolationException;
import java.util.List;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }


//@SpringBootApplication
//public class Application implements CommandLineRunner{
//    private UserService userService;
//    private TaskService taskService;
//    private SprintService sprintService;
//    private  ProgressService progressService;
//    private MarathonService marathonService;
//
//    public Application(
//            UserService userService,
//            TaskService taskService,
//            SprintService sprintService,
//            ProgressService progressService,
//            MarathonService marathonService
//    ) {
//        this.userService = userService;
//        this.taskService = taskService;
//        this.sprintService = sprintService;
//        this.progressService = progressService;
//        this.marathonService = marathonService;
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//    @Override
//    public void run(String... args) throws Exception {
//
//        System.out.println("=======   application started   ========");
//    }



   /* @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            System.out.println("*** START MAIN ***");
            System.out.println("***=== THE FIRST PART ===***");
            try {
                Marathon marathon1 = Marathon.builder().title("JOM_1").build();
                Marathon marathon2 = Marathon.builder().title("JOM_2").build();

                marathon1 = marathonService.createOrUpdate(marathon1);
                marathon2 = marathonService.createOrUpdate(marathon2);

                User student1 = User.builder().firstName("Lida")
                        .lastName("Black")
                        .email("black@gmail.com")
                        .password("password1")
                        .role(User.Role.TRAINEE)
                        .build();
                User student2 = User.builder()
                        .firstName("Nazar")
                        .lastName("Red")
                        .email("red@gmail.com")
                        .password("password2")
                        .role(User.Role.TRAINEE)
                        .build();

                User mentor1 = User.builder()
                        .firstName("Ivan")
                        .lastName("Blue")
                        .email("blue@gmail.com")
                        .password("password3")
                        .role(User.Role.MENTOR)
                        .build();

                student1 = userService.createOrUpdateUser(student1);
                student2 = userService.createOrUpdateUser(student2);
                mentor1 = userService.createOrUpdateUser(mentor1);

                userService.addUserToMarathon(student1, marathon1);
                userService.addUserToMarathon(mentor1, marathon1);

                userService.addUserToMarathon(student1, marathon2);
                userService.addUserToMarathon(student2, marathon2);
                userService.addUserToMarathon(mentor1, marathon2);


                marathonService.getAll().forEach(System.out::println);
                userService.getAll().forEach(System.out::println);

                //marathonService.deleteMarathonById(marathon2.getId());
                marathonService.getAll().forEach(System.out::println);


                System.out.println("***=== THE SECOND PART ===***");
                //1.Create two sprints
                Sprint sprint1 = Sprint.builder()
                        .title("Sprint 1")
                        .build();
                Sprint sprint2 = Sprint.builder()
                        .title("Sprint 2")
                        .build();
                //2.save sprints --> addSprintToMarathon
                sprintService.addSprintToMarathon(sprint1, marathon1);
                sprintService.addSprintToMarathon(sprint2, marathon2);

                //3. create 2 task and save ---> addTaskToSprint

                Task task1 = Task.builder().title("task1").build();
                Task task2 = Task.builder().title("task2").build();
                taskService.createOrUpdateTask(task1);
                taskService.createOrUpdateTask(task2);
                //4.create 3 progresses and save --> addTaskForStudent
                Progress progress1 = progressService.addTaskForStudent(task1, student1);
                Progress progress2 = progressService.addTaskForStudent(task2, student2);
                Progress progress3 = progressService.addTaskForStudent(task1, mentor1);

                //5. All methods with ProgressService!
                progressService.addOrUpdateProgress(progress1);
                progressService.setStatus(Progress.TaskStatus.PENDING, progress3);
                progressService.allProgressByUserIdAndSprintId(student1.getId(), sprint1.getId());
                progressService.allProgressByUserIdAndMarathonId(student2.getId(), sprint2.getId());
                progressService.addOrUpdateProgress(progress2);


                //6. All methods with SprintService!
                sprintService.getSprintById(sprint1.getId());
                sprintService.addSprintToMarathon(sprint1, marathon2);
                sprintService.updateSprint(sprint1);
                sprintService.getSprintById(7);

                //7. All methods with TaskService!
                taskService.addTaskToSprint(task1, sprint1);
                taskService.getTaskById(task1.getId());

            } catch (ConstraintViolationException e) {
                System.out.println("---CONSTRAINTS VIOLATIONS---\n" +
                        e.getMessage());
            }
            System.out.println("*** STOP MAIN ***");
        };
    }*/
}