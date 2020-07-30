package com.sprint.hibernate.controller;


import com.sprint.hibernate.entity.Progress;
import com.sprint.hibernate.service.ProgressService;
import com.sprint.hibernate.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Data
@AllArgsConstructor
public class ProgressController {

    ProgressService progressService;
    UserService userService;

    @GetMapping("/students/{marathon_id}/progress/{student_id}")
    public String removeFromMarathon(@PathVariable(name = "marathon_id") long marathonId,
                                     @PathVariable(name = "student_id") long studentId, Model model) {
        List<Progress> progress = progressService.allProgressByUserIdAndMarathonId(studentId, marathonId);
        model.addAttribute("progress", progress);
        model.addAttribute("student", userService.getUserById(studentId));
        return "student-marathon-progress";
    }
}
