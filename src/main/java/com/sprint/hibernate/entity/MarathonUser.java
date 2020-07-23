package com.sprint.hibernate.entity;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "marathon_user")
public class MarathonUser {

    @Id
    @ManyToOne
    private Marathon marathon;

    @Id
    @ManyToOne
    private Users user;
}
