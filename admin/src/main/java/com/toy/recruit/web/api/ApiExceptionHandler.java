package com.toy.recruit.web.api;

import com.toy.recruit.core.excpetion.AlertException;
import com.toy.recruit.core.excpetion.UnableChangeStatusException;
import com.toy.recruit.core.parameter.ValidationResponse;
import com.toy.recruit.core.validator.PasswordValidator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import static org.springframework.http.HttpStatus.FAILED_DEPENDENCY;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final MessageSource messageSource;
    private final PasswordValidator passwordValidator;

    @InitBinder("passwordDto")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(passwordValidator);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationResponse bindException(BindException exception, Locale locale) {
        return new ValidationResponse(exception.getBindingResult(), messageSource, locale);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnableChangeStatusException.class)
    public ValidationResponse unableChangeStatusException(UnableChangeStatusException exception, Locale locale) {
        String code = exception.getMessage();
        String message = messageSource.getMessage(code, null, locale);

        return new ValidationResponse(code, message);
    }

//    @ExceptionHandler(AlertException.class)
//    public ValidationResponse handleException(AlertException exception, Locale locale) {
//        String code = exception.getMessage();
//        String message = messageSource.getMessage(code, null, locale);
//
//        return new ValidationResponse(code, message);
//    }
}
