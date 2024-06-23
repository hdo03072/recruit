package com.toy.recruit.web.dto.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDto {

    @NotBlank
    private String password;

    @NotBlank
    private String confirm;
}
