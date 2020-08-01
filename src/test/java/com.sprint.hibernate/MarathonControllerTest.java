package com.sprint.hibernate;

import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.SprintService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MarathonControllerTest {

    private MockMvc mockMvc;
    private MarathonService marathonService;
    private SprintService sprintService;

    @Autowired
    public MarathonControllerTest(MockMvc mockMvc, MarathonService marathonService, SprintService sprintService) {
        this.marathonService=marathonService;
        this.mockMvc=mockMvc;
        this.sprintService=sprintService;
    }

    @Test
    public void getAllMarathonsTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/marathons"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("marathons"))
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attribute("marathons", marathonService.getAll()));
    }

    @Test
    public void testCreateMarathon() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/marathons/create/")
                .param("title", "Java"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testDeleteMarathon() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/marathons/delete/3"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testEditMarathon() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/marathons/edit/1")
                .param("title", "JOM_1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    @Test
    public void getAllSprintsTest() throws Exception {
        List<Sprint> expected = sprintService.getSprintsByMarathonId(1);

        mockMvc.perform(MockMvcRequestBuilders.get("/sprints/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("sprints"))
                .andExpect(MockMvcResultMatchers.model().size(4))
                .andExpect(MockMvcResultMatchers.model().attribute("sprints", expected));
    }

    @Test
   public void testCreateSprint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/sprints/add/1")
                .param("title", "NewSprint"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testDeleteSprint() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/sprints/delete/3/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testEditSprint() throws  Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/sprints/edit/1")
                .param("title", "EditedSprint"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

}
