package com.sprint.hibernate.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name="marathon")
public class Marathon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 3, max = 20, message = "Marathon title must be between 3 and 20 characters")
    private String title;
    @ToString.Exclude
    @OneToMany(mappedBy = "marathon")
    private List<Sprint> sprintList;
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "marathon_user",
            joinColumns = @JoinColumn(name = "marathon_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> users;
}
