package com.sprint.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private LocalDate created;
    private String title;
    private LocalDate updated;

    @ManyToOne
    private Sprint sprint;
    @OneToMany
    private List<Progress> progress;

}
