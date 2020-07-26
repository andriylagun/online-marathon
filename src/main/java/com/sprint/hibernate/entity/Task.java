package com.sprint.hibernate.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @CreationTimestamp
    private LocalDate created;
    @NotNull
    @Size(min = 5, max = 20, message = "Task tile must be between 5 and 20 characters")
    private String title;
    @UpdateTimestamp
    private LocalDate updated;

    @ManyToOne
    private Sprint sprint;
    @OneToOne
    private Progress progress;

}
