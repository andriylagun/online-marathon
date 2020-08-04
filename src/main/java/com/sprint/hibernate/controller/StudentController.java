package com.sprint.hibernate.controller;


import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@Controller
@Data
@AllArgsConstructor
@Transactional
@RequestMapping("/students")
public class StudentController {

    private UserService userService;
    private MarathonService marathonService;
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping
    public String getAllStudents(Model model) {
        logger.info("Get all students from marathon");
        List<User> students = userService.getAllByRole("TRAINEE");
        User newStudent = User.builder().role(User.Role.TRAINEE).build();
        model.addAttribute("newStudent", newStudent);
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/{marathon_id}")
    public String getAllStudentsFromMarathon(@PathVariable(name = "marathon_id") long marathonId, Model model) {
        logger.info("Getting all students from the marathon");
        List<User> students = userService.allUsersByMarathonIdAndRole(marathonId, "TRAINEE");
        User newStudent = new User();
        model.addAttribute("newStudent", newStudent);
        model.addAttribute("students", students);
        model.addAttribute("marathon", marathonService.getMarathonById(marathonId));
        return "marathon-students";
    }

    @GetMapping("/{marathon_id}/delete/{student_id}")
    public String removeFromMarathon(@PathVariable(name = "marathon_id") long marathonId,
                                     @PathVariable(name = "student_id") long studentId) {
        logger.info("Removing student from the marathon");
        userService.deleteUserFromMarathon(userService.getUserById(studentId), marathonService.getMarathonById(marathonId));
        return "redirect:/students/{marathon_id}";
    }

    @GetMapping("/delete/{student_id}")
    public String removeStudent(@PathVariable(name = "student_id") long studentId) {
        logger.info("Deleting student");
        userService.deleteUserById(studentId);
        return "redirect:/students";
    }

    @PostMapping("/edit/{student_id}")
    public String saveEditedStudent(@PathVariable(name = "student_id") long studentId,
                                    @ModelAttribute(name = "student") User student) {
        logger.info("Editing student");
        try {
            student.setId(studentId);
            student.setRole(User.Role.TRAINEE);
            userService.createOrUpdateUser(student);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return "redirect:/students";
    }


    @PostMapping("/add/{marathon_id}")
    public String addStudentToMarathon(@PathVariable(name = "marathon_id") long marathonId,
                                       @ModelAttribute(name = "student") User student) {
        logger.info("Adding student to the marathon");
        student.setRole(User.Role.valueOf("TRAINEE"));
        try {
            userService.createOrUpdateUser(student);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        userService.addUserToMarathon(student, marathonService.getMarathonById(marathonId));
        return "redirect:/students/{marathon_id}";
    }

    @GetMapping("/student/{student_id}")
    public String getInfoAboutStudent(@PathVariable(name = "student_id") long studentId, Model model) {
        logger.info("Getting info about student");
        User student = userService.getUserById(studentId);
        model.addAttribute("student", student);
        return "student";
    }
}






