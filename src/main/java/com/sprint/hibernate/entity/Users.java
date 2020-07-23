package com.sprint.hibernate.entity;

import javax.persistence.*;

@Table(name="users")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private String email;
    @Column
    private String password;

}
