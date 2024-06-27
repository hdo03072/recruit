package com.toy.recruit.core.excpetion;


import java.io.IOException;

public class AlertException extends Exception {
    public AlertException() throws IOException {}

    public AlertException(String message) throws IOException {
        super(message);
    }
}
