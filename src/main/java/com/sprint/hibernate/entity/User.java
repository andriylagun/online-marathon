package com.sprint.hibernate.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {

  public enum Role {
    MENTOR, TRAINEE //trainee мені не дуже подобається :) Може давай все таки Student?
  }

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private BigInteger id;   //UUID

  @Column(unique = true)
  @NotNull
  @Pattern(regexp = ".+@.+\\..+", message = "please provide a volid email address")
  private String email;

  @NotNull
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
  @Column(name = "last_name")
  private String lastName;

  @Column
  @NotNull
  private String password;

  @Column
  @NotNull
  @Enumerated(EnumType.STRING)
  private Role role;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.LAZY, mappedBy="users")
  /*Обновив твоє ManyToMany, тепер не потрібно використовувати окремої сутності для marathon_users
    Подивишся реалізацію в Marathon.
  */
  private List<Marathon> marathons;
}
