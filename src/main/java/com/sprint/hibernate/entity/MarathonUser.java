package com.sprint.hibernate.entity;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "marathon_user")
public class MarathonUser {

    @Id
    @OneToOne
    private Marathon marathon;

    @Id
    @OneToOne
    private Users user;
}
