package com.sprint.hibernate.controller;


import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.exceptions.MarathonExistException;
import com.sprint.hibernate.exceptions.SprintExistException;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.SprintService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MarathonController {

    private MarathonService marathonService;
    private SprintService sprintService;

    private final Logger logger = LoggerFactory.getLogger(MarathonController.class);

    @GetMapping("/marathons")
    public String getAllMarathons(Model model) {
        logger.info("That's info about students");
        List<Marathon> marathons = marathonService.getAll();
        Marathon marathon = new Marathon();
        model.addAttribute("marathon", marathon);
        model.addAttribute("marathons", marathons);
        return "marathons";
    }

    @GetMapping("/marathons/delete/{id}")
    public String deleteMarathon(@PathVariable(name = "id") Long id) {
        logger.info("YOU DELETING A USER, ARE YOU SURE?");
        marathonService.deleteMarathonById(id);
        return "redirect:/marathons";
    }

    @PostMapping("/marathons/create")
    public String addMarathon(@Valid @ModelAttribute(name = "marathon") Marathon marathon,
                              BindingResult bindingResult) {
        logger.info("You are creating new USER");
        if (bindingResult.hasErrors()) {
            logger.error(bindingResult.getAllErrors().toString());
            return "marathons";
        }
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @PostMapping("/marathons/edit/{id}")
    public String editMarathon(@ModelAttribute(name = "marathon") Marathon marathon){
        logger.info("You are editing existing user");
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }
}