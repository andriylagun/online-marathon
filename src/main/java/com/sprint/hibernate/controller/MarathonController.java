package com.sprint.hibernate.controller;


import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.SprintService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static javax.print.attribute.Size2DSyntax.MM;

@Controller
@Data
@AllArgsConstructor
public class MarathonController {

    private MarathonService marathonService;
    private SprintService sprintService;

    @GetMapping("/marathons")
    public String getAllMarathons(Model model) {
        List<Marathon> marathons = marathonService.getAll();
        Marathon marathon = new Marathon();
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

    @PostMapping("/marathons/edit/{id}")
    public String editMarathon(@ModelAttribute(name = "marathon") Marathon marathon) {
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }
    @GetMapping("/sprints/{id}")
    public String showSprints(@PathVariable(name="id") long id,
                              Model model){
        Marathon marathon = marathonService.getMarathonById(id);
        List<Sprint> sprints= marathon.getSprintList();
        Sprint newSprint= new Sprint();
        model.addAttribute("newSprint", newSprint);
        model.addAttribute("sprints", sprints);
        model.addAttribute("marathon", marathon);
        String welcome="Sprints of "+marathon.getTitle();
        model.addAttribute("welcome", welcome);
        return "sprints";
    }
    @PostMapping("/sprints/edit")
    public String editMarathon(@ModelAttribute(name ="sprint") Sprint sprint){
        sprintService.updateSprint(sprint);
        return "redirect:/marathons";
    }
    @PostMapping("/sprints/add/{id}")
    public String createSprint(@ModelAttribute(name ="marathon") Marathon marathon,
                               @ModelAttribute(name ="sprint") Sprint sprint) {
        sprintService.addSprintToMarathon(sprint, marathon);
        return "redirect:/marathons";
    }
}