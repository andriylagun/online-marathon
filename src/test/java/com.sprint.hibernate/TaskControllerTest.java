package com.sprint.hibernate;


import com.sprint.hibernate.service.SprintService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TaskControllerTest {

    private MockMvc mockMvc;
    private SprintService sprintService;

    @Autowired
    public TaskControllerTest(MockMvc mockMvc, SprintService sprintService) {
        this.mockMvc = mockMvc;
        this.sprintService = sprintService;
    }

    @Test
    public void getAllTasksTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("tasks"))
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attribute("tasks", Matchers.notNullValue()));
    }
}
