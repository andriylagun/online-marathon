package com.sprint.hibernate.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name="sprint")
@Entity
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="start_date")
    private LocalDate startDate;
    private LocalDate finish;
    private String title;

    @ManyToOne(optional=false)
    private Marathon marathon;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
