package com.sprint.hibernate.entity;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
@Data
@Table(name="sprint")
@Entity
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="start_date")
    private LocalDate startDate;
    private LocalDate finish;
    private String title;

    @ManyToOne(optional=false)
    private Marathon marathon;
    @ToString.Exclude
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    private List<Task> tasks;

}
