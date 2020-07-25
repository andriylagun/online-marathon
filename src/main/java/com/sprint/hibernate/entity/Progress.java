package com.sprint.hibernate.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
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

    @OneToOne
    private Task task;

    @ManyToOne(optional = false)
    private User trainee;

}
