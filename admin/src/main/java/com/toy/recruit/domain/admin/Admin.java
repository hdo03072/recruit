package com.toy.recruit.domain.admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "admin")
public class Admin {

    @Id
    @Column(length = 200, name = "admin_id")
    private String adminId;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 100, name = "role", nullable = false)
    private Role role;
}
