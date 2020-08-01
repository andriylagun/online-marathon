package com.sprint.hibernate.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "progress")
public class Progress {

    public enum TaskStatus {
        PASS, FAIL, PENDING
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne
    private Task task;

    @ManyToOne(optional = false)
    private User trainee;

}
