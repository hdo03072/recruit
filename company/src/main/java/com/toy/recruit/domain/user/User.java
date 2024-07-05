package com.toy.recruit.domain.user;

import com.toy.recruit.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.toy.recruit.core.code.Constants.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @Column(length = DB_MANY_NUM, name = "user_id")
    private String userId;

    @Column(length = DB_MAX_NUM, name = "password")
    private String password;

    @Column(length = DB_MID_NUM, name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = DB_MID_NUM, name = "role", nullable = false)
    private Role role;

    @Column(length = DB_MID_NUM, name = "phone_number")
    private String phoneNumber;

    @Column(length = DB_MID_NUM, name = "email")
    private String email;

    @Column(length = DB_TEN_NUM, name = "zip_code")
    private String zipCode;

    @Column(length = DB_MANY_NUM, name = "road_address")
    private String roadAddress;

    @Column(length = DB_MANY_NUM, name = "detail_address")
    private String detailAddress;


}
