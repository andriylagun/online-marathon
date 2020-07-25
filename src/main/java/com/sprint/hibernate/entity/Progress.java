package com.sprint.hibernate.entity;


import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "progress")
public class Progress {

    public enum TaskStatus {
        PASS, FAIL, PENDING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @CreationTimestamp
    private LocalDate started;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @NotNull
    @CreationTimestamp
    private LocalDate updated;

    @NotNull
    @OneToOne
    private Task task;

    @ManyToOne(optional = false)
    private User trainee;

}
