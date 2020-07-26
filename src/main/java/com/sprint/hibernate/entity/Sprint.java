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
@Table(name="sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="start_date")
    @NotNull
    @CreationTimestamp
    private LocalDate startDate;
    @UpdateTimestamp
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
