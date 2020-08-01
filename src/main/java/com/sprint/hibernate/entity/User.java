package com.sprint.hibernate.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
  public enum Role {
    MENTOR, TRAINEE
  }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

  @NotNull
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
  @Column(name = "last_name")
  private String lastName;

  @Column(unique = true)
  @NotNull
  @Pattern(regexp = ".+@.+\\..+", message = "please provide a volid email address")
  private String email;

  @Column
  @NotNull
  private String password;

  @Column
  @NotNull
  @Enumerated(EnumType.STRING)
  private Role role;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.LAZY, mappedBy="users")
  private List<Marathon> marathons;

  @OneToMany(mappedBy = "trainee")
  private List<Progress> progresses;

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//    if (o == null || getClass() != o.getClass()) return false;
//    User user = (User) o;
//    return getId() == user.getId() &&
//            getFirstName().equals(user.getFirstName()) &&
//            getLastName().equals(user.getLastName()) &&
//            getEmail().equals(user.getEmail()) &&
//            getPassword().equals(user.getPassword()) &&
//            getRole() == user.getRole() &&
//            Objects.equals(getMarathons(), user.getMarathons());
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getPassword(), getRole(), getMarathons());
//  }
}
