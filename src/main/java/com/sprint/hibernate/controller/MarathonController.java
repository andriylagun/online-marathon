package com.sprint.hibernate.controller;


import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.SprintService;
import com.sprint.hibernate.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/marathons")
@Controller
@AllArgsConstructor
public class MarathonController {

    private final Logger logger = LoggerFactory.getLogger(MarathonController.class);
    private MarathonService marathonService;

    @Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;


    @GetMapping
    public String getAllMarathons(Model model, Authentication authentication) {
        logger.info("That's info about students");
        List<Marathon> marathons = marathonService.getAll();
        User user = (User) userDetailsService.loadUserByUsername(authentication.getName());
        List<Marathon> userMarathons= user.getMarathons();
        Marathon marathon = new Marathon();
        model.addAttribute("userMarathons", userMarathons);
        model.addAttribute("marathon", marathon);
        model.addAttribute("marathons", marathons);
        return "marathons";
    }

    @GetMapping("/delete/{id}")
    public String deleteMarathon(@PathVariable(name = "id") Long id) {
        logger.info("YOU DELETING A MARATHON, ARE YOU SURE?");
        marathonService.deleteMarathonById(id);
        return "redirect:/marathons";
    }

    @PostMapping("/create")
    public String addMarathon(@Valid @ModelAttribute(name = "marathon") Marathon marathon,
                              BindingResult bindingResult) {
        logger.info("You are creating new marathon");
        if (bindingResult.hasErrors()) {
            logger.error(bindingResult.getAllErrors().toString());
            return "marathons";
        }
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.GET})
    public String editMarathon(Marathon marathon) {
        logger.info("You are editing existing marathon");
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @RequestMapping("getOne")
    @ResponseBody
    public Marathon getOne(Long id){
        return marathonService.getMarathonById(id);
    }
}