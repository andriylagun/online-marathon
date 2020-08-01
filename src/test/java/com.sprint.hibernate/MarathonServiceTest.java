package com.sprint.hibernate;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.User;
import com.sprint.hibernate.exceptions.MarathonExistException;
import com.sprint.hibernate.repository.MarathonRepository;
import com.sprint.hibernate.service.MarathonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarathonServiceTest {

    @Autowired
    private MarathonService marathonService;

    @MockBean
    private MarathonRepository marathonRepository;

    private static Marathon marathon1;

    @BeforeEach
    public void setUp(){
        marathon1 = Marathon.builder().id(3L).title("JOM_1").build();
    }

    @Test
    public void getMarathonByIdTest() {
        Mockito.when(marathonRepository.findById(1L)).thenReturn(Optional.of(marathon1));
        String expected = marathon1.toString();
        String actual = marathonService.getMarathonById(1L).toString();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void createOrUpdateMarathonTest() throws MarathonExistException {
        Marathon newMarathon= Marathon.builder()
                .id(1)
                .title("JOM_2").build();
        Mockito.when(marathonRepository.findAll()).thenReturn(List.of(marathon1,newMarathon));
        List<Marathon> actual = marathonService.getAll();
        List<Marathon> expected = List.of(marathon1, newMarathon);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void deleteMarathonTest(){
        marathonService.deleteMarathonById(1L);
        marathonService.deleteMarathonById(1L);
        Mockito.verify(marathonRepository, Mockito.times(2)).deleteById(1L);
    }
    @Test
    public void deleteAllTest(){
        marathonService.deleteAll();
        marathonService.deleteAll();
        Mockito.verify(marathonRepository, Mockito.times(2)).deleteAll();
    }
    @Test
    public void checkTitle(){
        marathonService.checkTitle("JOM_1");
        Mockito.when(marathonRepository.getMarathonByTitle("JOM_1")).thenReturn(marathon1);
        Mockito.verify(marathonRepository).getMarathonByTitle("JOM_1");
    }
}
