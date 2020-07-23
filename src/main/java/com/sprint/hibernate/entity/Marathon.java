package com.sprint.hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="marathon")
public class Marathon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @OneToMany(mappedBy = "marathon")
    private List<Sprint> sprintList;

    @OneToMany
    private List<MarathonUser> marathonUsers;
}
