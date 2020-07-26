package com.sprint.hibernate.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Sprint> getSprintList() {
        return sprintList;
    }

    public void setSprintList(List<Sprint> sprintList) {
        this.sprintList = sprintList;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
