package com.sprint.hibernate.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity(name="roles")
public class Role implements GrantedAuthority {
    public enum Name {
        MENTOR, TRAINEE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
