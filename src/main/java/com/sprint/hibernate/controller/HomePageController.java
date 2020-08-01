package com.sprint.hibernate.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
@AllArgsConstructor
public class HomePageController {

    @GetMapping({"/","/home"})
    public String homePage() {
        return "../static/index";
    }

}
