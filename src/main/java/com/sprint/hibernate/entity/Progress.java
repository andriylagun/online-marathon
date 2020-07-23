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
    @Column
    private LocalDateTime started;
    @Column
    private String status;
    @Column
    private LocalDateTime updated;

    @OneToOne(mappedBy = "progress")
    @JoinColumn(name = "task_id")
    private BigInteger taskId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "trainee_id")
    private BigInteger traineeId;

    public Progress() {
    }
}
