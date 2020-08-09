package com.sprint.hibernate.controller;


import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.RoleService;
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

@Controller
@Data
@AllArgsConstructor
@Transactional
@RequestMapping("/students")
public class StudentController {

    private UserService userService;
    private MarathonService marathonService;
    private RoleService roleService;
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping
    public String getAllStudents(Model model) {
        logger.info("Get all students");
        List<User> students = userService.getAllByRoleId(1);
        List<User> mentors = userService.getAllByRoleId(2);
        User newStudent = new User();
        model.addAttribute("mentors", mentors);
        model.addAttribute("newStudent", newStudent);
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/{marathon_id}")
    public String getAllStudentsFromMarathon(@PathVariable(name = "marathon_id") long marathonId, Model model) {
        logger.info("Getting all students from the marathon");
        List<User> students = userService.allUsersByMarathonIdAndRoleId(marathonId, 1);
        List<User> allStudents = userService.getAll();
        User addStudent = new User().builder().role(roleService.getRoleById(1)).build();
        model.addAttribute("addStudent", addStudent);
        model.addAttribute("students", students);
        model.addAttribute("marathon", marathonService.getMarathonById(marathonId));
        model.addAttribute("allStudents", allStudents);
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

    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.GET})
    public String saveEditedStudent(User student) {
            logger.info("Editing student");
            student.setRole(roleService.getRoleById(1));
            userService.createOrUpdateUser(student);
        return "redirect:/students";
    }

    @GetMapping("/{marathon_id}/add")
    public String addExistingStudentToMarathon(@RequestParam("user_id") long userId, @PathVariable("marathon_id") long marathonId) {
        userService.addUserToMarathon(
                userService.getUserById(userId),
                marathonService.getMarathonById(marathonId));
        return "redirect:/students/{marathon_id}";
    }
    @PostMapping("/add")
    public String createStudent(@ModelAttribute(name="newStudent") User student) {
            student.setRole(roleService.getRoleById(1));
            userService.createOrUpdateUser(student);
        return "redirect:/students/";
    }
    @PostMapping("/{marathon_id}/add")
    public String addStudentToMarathon(@ModelAttribute(name="newStudent") User student,
                                       @PathVariable(name="marathon_id")long marathonId) {
        student.setRole(roleService.getRoleById(1));
        userService.createOrUpdateUser(student);
        userService.addUserToMarathon(student,
                marathonService.getMarathonById(marathonId));
        return "redirect:/students/"+ marathonId;
    }

    @GetMapping("/student/{student_id}")
    public String getInfoAboutStudent(@PathVariable(name = "student_id") long studentId, Model model) {
        logger.info("Getting info about student");
        User student = userService.getUserById(studentId);
        model.addAttribute("student", student);
        return "student";
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public User getOne(Long id){
        return userService.getUserById(id);
    }
}






