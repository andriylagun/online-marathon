package com.sprint.hibernate.entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "progress")
public class Progress {

    public enum TaskStatus {
        PASS, FAIL, PENDING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @CreationTimestamp
    private LocalDate started;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @CreationTimestamp
    private LocalDate updated;

    @ManyToOne
    private Task task;

    @ManyToOne(optional = false)
    private Users trainee;

}
