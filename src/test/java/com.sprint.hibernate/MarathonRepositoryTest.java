package com.sprint.hibernate;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.repository.MarathonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarathonRepositoryTest {

    private MarathonRepository marathonRepository;

    @Autowired
    public MarathonRepositoryTest(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @Test
    public void testCheckTitle(){
        Marathon marathon= Marathon.builder()
                .id(1)
                .title("JOM_1")
                .build();
        Assertions.assertEquals(marathon,marathonRepository.getMarathonByTitle("JOM_1"));
    }

}
