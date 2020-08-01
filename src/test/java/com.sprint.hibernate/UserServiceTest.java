package com.sprint.hibernate;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.repository.MarathonRepository;
import com.sprint.hibernate.repository.UserRepository;
import com.sprint.hibernate.service.*;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MarathonRepository marathonRepository;

    private static User student1;
    private static User student2;
    private static User student3;
    private static User mentor1;
    private static User mentor2;
    private static Marathon marathon1;

    @BeforeEach
    public void setUp() {

        marathon1 = Marathon.builder().id(1).title("JOM_1").build();
        student1 = User.builder().firstName("Uliana")
                .id(1)
                .lastName("Tomyn")
                .email("uliana@gmail.com")
                .password("password1")
                .role(User.Role.TRAINEE)
                .build();
        student2 = User.builder()
                .id(2)
                .firstName("Andriy")
                .lastName("Lagun")
                .email("andriy@gmail.com")
                .password("password2")
                .role(User.Role.TRAINEE)
                .build();
        student3 = User.builder()
                .id(3)
                .firstName("Olesia")
                .lastName("Setrina")
                .email("olesia@gmail.com")
                .password("password3")
                .role(User.Role.TRAINEE)
                .build();
        mentor1 = User.builder()
                .id(4)
                .firstName("Mykola")
                .lastName("Demchyna")
                .email("mykola@gmail.com")
                .password("password4")
                .role(User.Role.MENTOR)
                .build();
        mentor2 = User.builder()
                .id(5)
                .firstName("Nataliia")
                .lastName("Romanenko")
                .email("nataliia@gmail.com")
                .password("password5")
                .role(User.Role.MENTOR)
                .build();

        student1.setMarathons(List.of(marathon1));
        student2.setMarathons(List.of(marathon1));
        mentor1.setMarathons(List.of(marathon1));

        marathon1.setUsers(List.of(student1, student2));
        student1.setMarathons(List.of(marathon1));
        student2.setMarathons(List.of(marathon1));

        Mockito.when(userRepository.findById(student1.getId()))
                .thenReturn(Optional.of(student1));

        Mockito.when(userRepository.findById(student3.getId()))
                .thenReturn(Optional.of(student3));

        Mockito.when(userRepository.getAllByRole(User.Role.valueOf("TRAINEE")))
                .thenReturn(List.of(student1, student2, student3));

        Mockito.when(userRepository.findAllByMarathonsIdAndRole(1L, User.Role.valueOf("TRAINEE")))
                .thenReturn(List.of(student1, student2));

        Mockito.when(marathonRepository.findById(marathon1.getId()))
                .thenReturn(Optional.of(marathon1));

    }

    @Test
    public void getUserByIdTest() {
        String expected = student1.toString();
        String actual = userService.getUserById(student1.getId()).toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllTest() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(student1, student2, student3, mentor1, mentor2));
        String expected = List.of(student1, student2, student3, mentor1, mentor2).toString();
        String actual = userService.getAll().toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getAllByRoleTest() {
        String expected = List.of(student1, student2, student3).toString();
        String actual = userService.getAllByRole("TRAINEE").toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void allUsersByMarathonIdAndRoleTest() {
        String expected = List.of(student1, student2).toString();
        String actual = userService.allUsersByMarathonIdAndRole(1L, "TRAINEE").toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void deleteAllTest() {
        doNothing().when(userRepository).deleteAll();
        userService.deleteAll();
        verify(userRepository, times(1)).deleteAll();
    }

    @Test
    public void createOrUpdateUserTest() throws NoSuchFieldException, IllegalAccessException {
        Mockito.when(userRepository.getOne(1L))
                .thenReturn(student1);
        userService.createOrUpdateUser(student1);
        verify(userRepository, times(1)).save(student1);
    }

    @Test
    public void addUserToMarathonTest() {
        boolean expected = false;
        User student4 = User.builder()
                .id(4)
                .firstName("Olesia")
                .lastName("Setrina")
                .email("olesia@gmail.com")
                .password("password3")
                .role(User.Role.TRAINEE)
                .build();
        boolean actual = userService.addUserToMarathon(student4, marathon1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void deleteUserFromMarathonTest() {
        boolean expected = false;
        Marathon marathon3 = Marathon.builder().id(3).title("JOM_3").build();
        boolean actual = userService.deleteUserFromMarathon(student1, marathon3);
        Assertions.assertEquals(expected, actual);
    }

}
