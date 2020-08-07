package com.sprint.hibernate.controller;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.SprintService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@AllArgsConstructor
public class SprintController {

    private final Logger logger = LoggerFactory.getLogger(SprintController.class);
    private MarathonService marathonService;
    private SprintService sprintService;

    @GetMapping("/sprints/{id}")
    public String showSprints(@PathVariable(name = "id") long id,
                              Model model) {
        logger.info("That is info about sprints");
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

    @PostMapping("/sprints/add/{id}")
    public String createSprint(@ModelAttribute(name = "marathon") Marathon marathon,
                               @ModelAttribute(name = "newSprint") Sprint sprint) {
        logger.info("Add a new sprint");
        Sprint sprint1 = Sprint.builder()
                .title(sprint.getTitle())
                .build();
        sprintService.addSprintToMarathon(sprint1, marathon);
        return "redirect:/sprints/{id}";
    }

    @GetMapping("/sprints/delete/{id}/{mid}")
    public String deleteSprint(@PathVariable(name = "id") long id,
                               @PathVariable(name = "mid") long mid) {
        logger.info("You are deleting a sprint");
        sprintService.deleteSprintById(id, marathonService.getMarathonById(mid));
        return "redirect:/sprints/{mid}";
    }
}
