package com.sprint.hibernate.controller;


import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
@AllArgsConstructor
public class StudentController {

    private UserService userService;
    private MarathonService marathonService;

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        List<User> students = userService.getAllByRole("TRAINEE");
        User newStudent=User.builder().role(User.Role.TRAINEE).build();
        model.addAttribute("newStudent", newStudent);
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/students/{marathon_id}")
    public String getAllStudentsFromMarathon (@PathVariable(name="marathon_id") long marathonId, Model model) {
        List<User> students = userService.allUsersByMarathonIdAndRole(marathonId, "TRAINEE");
        model.addAttribute("students", students);
        model.addAttribute("marathon", marathonService.getMarathonById(marathonId));
        return "marathon-students";
    }

    @GetMapping("/students/{marathon_id}/delete/{student_id}")
    public String removeFromMarathon(@PathVariable(name="marathon_id") long marathonId,
                                     @PathVariable(name="student_id") long studentId) {
        userService.deleteUserFromMarathon(userService.getUserById(studentId), marathonService.getMarathonById(marathonId));
        return "redirect:/students/{marathon_id}";
    }

    @GetMapping("/students/delete/{student_id}")
    public String removeStudent(@PathVariable(name="student_id") long studentId) {

        userService.deleteUserById(studentId);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{student_id}")
    public String editStudent(@PathVariable(name="student_id") long studentId, Model model) {
        User student = userService.getUserById(studentId);
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/students/edit/{student_id}")
    public String saveEditedStudent(@PathVariable(name="student_id") long studentId,
                                    @ModelAttribute(name="student") User student) {
        try {
            student.setId(studentId);
            student.setRole(User.Role.TRAINEE);
            userService.createOrUpdateUser(student);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "redirect:/students";
    }

    @GetMapping("/students/{marathon_id}/add")
    public String creatStudent(@PathVariable(name="marathon_id") long marathonId, Model model) {
        User newStudent = new User();
        model.addAttribute("student", newStudent);
        model.addAttribute("marathon", marathonService.getMarathonById(marathonId));
        return "add-student";
    }

    @PostMapping("/students/add/{marathon_id}")
    public String addStudentToMarathon(@PathVariable(name="marathon_id") long marathonId,
                                       @ModelAttribute(name="student") User student) {
        student.setRole(User.Role.valueOf("TRAINEE"));
        try {
            userService.createOrUpdateUser(student);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        userService.addUserToMarathon(student, marathonService.getMarathonById(marathonId));
        return "redirect:/students/{marathon_id}";
    }

    @GetMapping("/student/{student_id}")
    public String getInfoAboutStudent(@PathVariable(name="student_id") long studentId, Model model) {
        User student = userService.getUserById(studentId);
        model.addAttribute("student", student);
        return "student";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        return "../static/index";
    }

    //    @GetMapping("/students/{marathon_id}/edit/{student_id}")
//    public String editStudent(@PathVariable(name="marathon_id") long marathonId,
//                              @PathVariable(name="student_id") long studentId, Model model) {
//        User student = userService.getUserById(studentId);
//        model.addAttribute("student", student);
//        model.addAttribute("marathon", marathonService.getMarathonById(marathonId));
//        return "edit-student";
//    }

    //    @PostMapping("/students/{marathon_id}/edit/{student_id}")
//    public String saveEditedStudent(@PathVariable(name="marathon_id") long marathonId,
//                                    @PathVariable(name="student_id") long studentId,
//                                    @ModelAttribute(name="student") User student) {
//        try {
//            userService.createOrUpdateUser(userService.getUserById(studentId));
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
////        userService.addUserToMarathon(student, marathonService.getMarathonById(marathonId));
//        return "redirect:/students/{marathon_id}";
//    }

}






