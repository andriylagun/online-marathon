package com.sprint.hibernate.entity;


import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;


    private LocalDateTime started;
    private String status;
    private LocalDateTime updated;

    @ManyToOne
    private Task task;

    @ManyToOne(optional = false)
    private Users user;

}
