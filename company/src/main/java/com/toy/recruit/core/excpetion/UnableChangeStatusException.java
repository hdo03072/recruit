package com.toy.recruit.core.excpetion;

public class UnableChangeStatusException extends Exception {
    public UnableChangeStatusException() {
    }

    public UnableChangeStatusException(String message) {
        super(message);
    }
}
