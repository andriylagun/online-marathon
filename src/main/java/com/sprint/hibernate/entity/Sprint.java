package com.sprint.hibernate.entity;


import com.sun.istack.NotNull;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;
        Sprint sprint = (Sprint) o;
        return Objects.equals(getStartDate(), sprint.getStartDate()) &&
                Objects.equals(getFinish(), sprint.getFinish()) &&
                Objects.equals(getTitle(), sprint.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getFinish(), getTitle());
    }
}
