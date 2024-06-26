package com.toy.recruit.web.api;

import com.toy.recruit.core.excpetion.UnableChangeStatusException;
import com.toy.recruit.core.parameter.ValidationResponse;
import com.toy.recruit.core.validator.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Locale;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public ValidationResponse IOException(IOException exception) {
        String code = exception.getMessage();
        String message = "파일 업로드에 실패하였습니다.";

        return new ValidationResponse(code, message);
    }
}
