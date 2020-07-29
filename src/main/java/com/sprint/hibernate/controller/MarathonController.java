package com.sprint.hibernate.controller;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.SprintService;
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

    private static final String REDIRECT_TO_MARATHONS = "redirect:/marathons";
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
        return REDIRECT_TO_MARATHONS;
    }

    @PostMapping("/marathons/create")
    public String addMarathon(@ModelAttribute(name = "marathon") Marathon marathon) {
        marathonService.createOrUpdate(marathon);
        return REDIRECT_TO_MARATHONS;
    }`

    @GetMapping("/sprints/{id}")
    public String showSprints(@PathVariable(name = "id") long id,
                              Model model) {
        Marathon sprintMarathon = marathonService.getMarathonById(id);
        List<Sprint> sprints = sprintMarathon.getSprintList();
        Sprint newSprint = new Sprint();
        model.addAttribute("newSprint", newSprint);
        model.addAttribute("sprints", sprints);
        model.addAttribute("sprintMarathon", sprintMarathon);
        String welcome = "Sprints of " + sprintMarathon.getTitle();
        model.addAttribute("welcome", welcome);
        return "sprints";
    }

    @PostMapping("/sprints/add/{marathon_id}")
    public String createSprint(@ModelAttribute(name = "marathon") Marathon marathon,
                               @ModelAttribute(name = "newSprint") Sprint sprint) {
        Sprint sprint1 = Sprint.builder()
                .title(sprint.getTitle())
                .build();
        sprintService.addSprintToMarathon(sprint1, marathon);
        return "redirect:/sprints/{marathon_id}";
    }

    @GetMapping("/sprints/delete/{sprint_id}/{marathon_id}")
    public String deleteSprint(@PathVariable(name = "sprint_id") long sprintId,
                               @PathVariable(name = "marathon_id") long marathonId) {
        sprintService.deleteSprintById(sprintId, marathonService.getMarathonById(marathonId));
        return "redirect:/sprints/{marathon_id}";
    }
}