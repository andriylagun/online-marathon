package com.sprint.hibernate;

import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class StudentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    //Fail
    @Test
    public void getAllStudentsTest() throws Exception {
        String expected = userService.getAllByRole("TRAINEE").toString();

        MvcResult result = this.mockMvc.perform(get("/students"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
                .andReturn();
        String actual = result.getModelAndView().getModel().get("students").toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getAllStudentsFromMarathonTest() throws Exception {
        List<User> students = userService.allUsersByMarathonIdAndRole(1, "TRAINEE");
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attribute("students", students));
    }

    //Fail
    @Test
    public void getInfoAboutStudentTest() throws Exception {
        User student = userService.getUserById(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
                .andExpect(MockMvcResultMatchers.model().attribute("student", student));
    }


//    @GetMapping("/students/delete/{student_id}")
//    public String removeStudent
//
//    @PostMapping("/students/edit/{student_id}")
//    public String saveEditedStudent
//
//    @PostMapping("/students/add/{marathon_id}")
//    public String addStudentToMarathon







    // we should add to the controller method add student (not to marathon)
    //test fails
    @Test
    public void addStudentTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
                .param("email", "test@email.com")
                .param("firstname", "fName")
                .param("lastname", "lName"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        
    }

//    @GetMapping("/students/{marathon_id}/delete/{student_id}")
//    @Test
//    public void removeFromMarathonTest() throws Exception {
//        List<User> students = userService.allUsersByMarathonIdAndRole(1, "TRAINEE");
//        mockMvc.perform(MockMvcRequestBuilders.get("/students/1/delete/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
//                .andExpect(MockMvcResultMatchers.model().size(3))
//                .andExpect(MockMvcResultMatchers.model().attribute("students", students));
//    }



}
