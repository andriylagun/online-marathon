package com.sprint.hibernate.entity;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "marathon_user")
public class MarathonUser {

    @Id
    @OneToOne(mappedBy = "marathon_user")
    @JoinColumn(name = "marathon_id")
    private BigInteger marathonId;

    @Id
    @OneToOne(mappedBy = "marathon_user")
    @JoinColumn(name = "user_id")
    private BigInteger userId;
}
