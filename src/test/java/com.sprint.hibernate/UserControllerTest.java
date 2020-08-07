package com.sprint.hibernate;

import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.service.UserService;
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
public class UserControllerTest {

    private MockMvc mockMvc;
    private UserService userService;

    @Autowired
    public UserControllerTest(MockMvc mockMvc, UserService userService) {
        this.mockMvc=mockMvc;
        this.userService = userService;
    }


//    @Test
//    public void getAllStudentsFromMarathonTest() throws Exception {
//        List<User> students = userService.allUsersByMarathonIdAndRole(1, "TRAINEE");
//        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
//                .andExpect(MockMvcResultMatchers.model().size(3))
//                .andExpect(MockMvcResultMatchers.model().attribute("students", students));
//    }
//
//
//    @Test
//    public void getInfoAboutStudentTest() throws Exception {
//        User student = userService.getUserById(1);
//        mockMvc.perform(MockMvcRequestBuilders.get("/students/student/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
//                .andExpect(MockMvcResultMatchers.model().attribute("student", student));
//    }
//
//    @Test
//    public void removeFromMarathonTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/students/1/delete/1"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//    }
//
//    @Test
//    public void addStudentTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/students/add/1")
//                .param("email", "test@email.com")
//                .param("firstName", "fName")
//                .param("lastName", "lName")
//                .param("password", "password"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//    }
//
//    @Test
//    public void getAllStudentsTest() throws Exception {
//        List<User> expected = userService.getAllByRole("TRAINEE");
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
//                .andExpect(MockMvcResultMatchers.model().attribute("students", expected));
//    }
//
//    @Test
//    public void saveEditedStudentTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/students/edit/1"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//    }

}
