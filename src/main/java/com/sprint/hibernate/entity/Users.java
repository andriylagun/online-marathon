package com.sprint.hibernate.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @OneToMany(mappedBy = "users")
    private BigInteger id;
    @Column
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String password;
    @Column
    private String role;

}
