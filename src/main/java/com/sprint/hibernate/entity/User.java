package com.sprint.hibernate.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="users")
public class User implements UserDetails {
//  public enum Role {
//    MENTOR, TRAINEE
//  }

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

  //@Enumerated(EnumType.STRING)
  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name="role_id", nullable = false)
  private Role role;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.LAZY, mappedBy="users")
  private List<Marathon> marathons;

  @ToString.Exclude
  @OneToMany(fetch=FetchType.EAGER, mappedBy = "trainee")
  private List<Progress> progresses;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return  getFirstName().equals(user.getFirstName()) &&
            getLastName().equals(user.getLastName()) &&
            getEmail().equals(user.getEmail()) &&
            getPassword().equals(user.getPassword()) &&
            getRole() == user.getRole();
  }


  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getLastName(), getEmail(), getPassword(), getRole());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(role);
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
