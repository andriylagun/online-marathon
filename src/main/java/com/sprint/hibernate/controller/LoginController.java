package com.sprint.hibernate.controller;

import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.service.RoleService;
import com.sprint.hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        User student=new User().builder().role(roleService.getRoleById(1)).build();
        model.addAttribute("student", student);
        return "login";
    }
    @PostMapping("/registration")
    public String register(@ModelAttribute(name="student") User student){
        student.setRole(roleService.getRoleById(1));
        userService.registerStudent(student);
        return "redirect:/login";
    }
}
