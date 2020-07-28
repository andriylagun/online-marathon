package com.sprint.hibernate.controller;

import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private MarathonService marathonService;

    //    List of all students (page available via .. /students, method GET)
    // but from some marathon!
    @GetMapping("/students")
    public String getAllStudents(Model model) {
        List<User> students = userService.getAllByRole("TRAINEE");
        model.addAttribute("students", students);
        return "students";
    }

    // List of all students from some marathon (page available via .. /students/{marathon_id}, method GET)
    @GetMapping("/students/{marathon_id}")
    public String getAllStudentsFromMarathon (@PathVariable(name="marathon_id") long marathonId, Model model) {
        List<User> students = userService.allUsersByMarathonIdAndRole(marathonId, "TRAINEE");
        model.addAttribute("students", students);
        return "students";
    }

    // When a route is like ../students/{marathon_id}/delete/{student_id},  then student with corresponding
    // student_id should be deleted from marathon with marathon_id
    @DeleteMapping("/students/{marathon_id}/delete/{student_id}")
    public String removeFromMarathon(@PathVariable(name="marathon_id") long marathonId,
                                     @PathVariable(name="student_id") long studentId) {
        userService.deleteUserFromMarathon(userService.getUserById(studentId), marathonService.getMarathonById(marathonId));
        return "redirect:/students";
    }


    // When a route is like ../students/{marathon_id}/edit/{student_id}, then show edit mode
    // of a student with student_id
    @GetMapping("/students/{marathon_id}/edit/{student_id}")
    public String editStudent(@PathVariable(name="marathon_id") long marathonId,
                              @PathVariable(name="student_id") long studentId) {
        //?????????????????????????????????????????????
        return "edit_student";
    }

    @GetMapping("/students/{marathon_id}/add")
    public String creatStudent(@PathVariable(name="marathon_id") long marathonId, Model model) {
        User student = new User();
        model.addAttribute("student", student);
        model.addAttribute("marathon_id", marathonId);
        return "add_student";
    }

    // Add student to marathon (route is like ../students/{marathon_id}/add)
    @PostMapping("/students/{marathon_id}/add")
    public String addStudentToMarathon(@PathVariable(name="marathon_id") long marathonId,
                                       @ModelAttribute(name="student") User student) {
        userService.addUserToMarathon(student, marathonService.getMarathonById(marathonId));
        return "redirect:/students/{marathon_id}";
    }

    //When user clicks on student name ‘Student’ page with filled data about selected student should be opened
    @GetMapping("/student/{student_id}")
    public String getInfoAboutStudent(@PathVariable(name="student_id") long studentId, Model model) {
        User student = userService.getUserById(studentId);
        model.addAttribute("student", student);
        return "student";
    }

}






