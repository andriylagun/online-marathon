package com.sprint.hibernate.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate created;
    private String title;
    private LocalDate updated;

    @ManyToOne
    private Sprint sprint;
    @OneToOne
    private Progress progress;

}
