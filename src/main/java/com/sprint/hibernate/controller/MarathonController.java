package com.sprint.hibernate.controller;


import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.service.MarathonService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Data
@AllArgsConstructor
public class MarathonController {

    private MarathonService marathonService;

    @GetMapping("/marathons")
    public String getAllMarathons(Model model) {
        List<Marathon> marathons = marathonService.getAll();
        Marathon marathon = new Marathon();
        String title="";
        model.addAttribute("title", title);
        model.addAttribute("marathon", marathon);
        model.addAttribute("marathons", marathons);
        return "marathons";
    }

    @GetMapping("/marathons/delete/{id}")
    public String deleteMarathon(@PathVariable(name = "id") Long id) {
        marathonService.deleteMarathonById(id);
        return "redirect:/marathons";
    }

    @PostMapping("/marathons/create")
    public String addMarathon(@ModelAttribute(name = "marathon") Marathon marathon) {
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @GetMapping("/marathons/edit/{id}")
    public String editMarathons(@PathVariable(name = "id") long id,Model model) {
        Marathon marathon = marathonService.getMarathonById(id);
        model.addAttribute("marathon",marathon);
        return "edit_marathon";
    }
    @PostMapping("/marathons/edit")
    public String editMarathon(@ModelAttribute(name = "marathon") Marathon marathon) {
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }
}