package com.sprint.hibernate.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @NotNull
    private LocalDate created;
    @NotNull
    @Size(min = 5, max = 20, message = "Task tile must be between 5 and 20 characters")
    private String title;
    @NotNull
    private LocalDate updated;

    @ManyToOne
    private Sprint sprint;
    @OneToOne
    private Progress progress;

}
