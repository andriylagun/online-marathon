package com.sprint.hibernate.controller;


import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.exceptions.MarathonExistException;
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
    public String addMarathon(@ModelAttribute(name = "marathon") Marathon marathon) throws MarathonExistException {
        logger.info("You are creating new USER");
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @PostMapping("/marathons/edit/{id}")
    public String editMarathon(@ModelAttribute(name = "marathon") Marathon marathon) throws MarathonExistException {
        logger.info("You are editing existing user");
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }
    @GetMapping("/sprints/{id}")
    public String showSprints(@PathVariable(name="id") long id,
                              Model model){
        logger.info("Thats info about sprints");
        Marathon sprintMarathon = marathonService.getMarathonById(id);
        List<Sprint> sprints= sprintMarathon.getSprintList();
        Sprint newSprint= new Sprint();
        model.addAttribute("newSprint", newSprint);
        model.addAttribute("sprints", sprints);
        model.addAttribute("sprintMarathon", sprintMarathon);
        String welcome="Sprints of "+sprintMarathon.getTitle();
        model.addAttribute("welcome", welcome);
        return "sprints";
    }
    @PostMapping("/sprints/edit/{mid}")
    public String editSprint(@ModelAttribute(name ="newSprint") Sprint sprint,
                             @PathVariable(name= "mid") long id){
        logger.info("Editing existing sprint");
        Sprint sprint1 = Sprint.builder()
                .id(sprint.getId())
                .title(sprint.getTitle())
                .marathon(marathonService.getMarathonById(id)).build();
        sprintService.updateSprint(sprint1);
        return "redirect:/sprints/{mid}";
    }
    @PostMapping("/sprints/add/{id}")
    public String createSprint(@ModelAttribute(name ="marathon") Marathon marathon,
                               @ModelAttribute(name ="newSprint") Sprint sprint) {
        logger.info("Add a new sprint");
        Sprint sprint1=Sprint.builder()
                .title(sprint.getTitle())
                .build();
        sprintService.addSprintToMarathon(sprint1, marathon);
        return "redirect:/sprints/{id}";
    }
    @GetMapping("/sprints/delete/{id}/{mid}")
    public String deleteSprint(@PathVariable(name = "id") long id,
                               @PathVariable(name = "mid") long mid) {
        logger.info("You are deleting a sprint");
        sprintService.deleteSprintById(id,marathonService.getMarathonById(mid));
        return "redirect:/sprints/{mid}";
    }
}