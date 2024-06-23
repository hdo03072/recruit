package com.toy.recruit.web.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ViewExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public String entityNotFoundException() {
        return "error/notFountElement";
    }
}
