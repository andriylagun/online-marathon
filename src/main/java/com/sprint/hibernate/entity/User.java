package com.sprint.hibernate.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
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

  /*public static Builder builder(){
    return new Builder();
  }
  public static class Builder{
   private User newUser;

    public Builder() {
      newUser=new User();
    }
    public Builder firstName(String firstName){
      newUser.firstName=firstName;
      return this;
    }
    public Builder lastName(String lastName){
      newUser.lastName=lastName;
      return this;
    }
    public Builder email(String email){
      newUser.email=email;
      return this;
    }
    public Builder password(String password){
      newUser.password=password;
      return this;
    }
    public Builder role(User.Role role){
      newUser.role=role;
      return this;
    }
    public User build(){
      return newUser;
    }
  }*/
}
