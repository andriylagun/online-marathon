package com.sprint.hibernate.entity;

import com.sun.istack.NotNull;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;


@Entity
@Table(name="users")
public class User {

    public enum Role {
        MENTOR, TRAINEE
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
    @Size(min = 2, max = 20, message = "last name must be between 2 and 20 characters")
    @Column(name = "last_name")
    private String lastName;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @ToString.Exclude     //Lombok
    @ManyToMany
    private List<Marathon> marathons;

}
