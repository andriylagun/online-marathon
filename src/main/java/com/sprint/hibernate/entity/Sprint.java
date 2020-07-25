package com.sprint.hibernate.entity;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate finish;
    @NotNull
    @Size(min = 7, max = 20, message = "Sprint title must be between 7 and 20 characters")
    private String title;

    @ManyToOne(optional=false)
    private Marathon marathon;
    @ToString.Exclude
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    private List<Task> tasks;

}
