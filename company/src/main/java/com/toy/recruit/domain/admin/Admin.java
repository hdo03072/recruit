package com.toy.recruit.domain.admin;

import com.toy.recruit.core.domain.BaseEntity;
import com.toy.recruit.domain.user.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.toy.recruit.core.code.Constants.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "admin")
public class Admin extends BaseEntity {

    @Id
    @Column(length = DB_MANY_NUM, name = "admin_id")
    private String adminId;

    @Column(length = DB_MAX_NUM, nullable = false, name = "password")
    private String password;

    @Column(length = DB_FEW_NUM, nullable = false, name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = DB_MID_NUM, name = "role", nullable = false)
    private Role role;
}
