package com.toy.recruit.core.validator;

import com.toy.recruit.web.dto.admin.PasswordDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordDto passwordDto = (PasswordDto) target;

        if (passwordDto.getPassword().isBlank() || passwordDto.getConfirm().isBlank()) {
            return;
        }

        if (!passwordDto.getPassword().equals(passwordDto.getConfirm())) {
            errors.reject("password.mismatch");
        }
    }
}
