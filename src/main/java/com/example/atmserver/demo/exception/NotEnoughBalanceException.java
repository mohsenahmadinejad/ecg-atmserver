package com.example.atmserver.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotEnoughBalanceException extends RuntimeException {
    private String Message;

    public NotEnoughBalanceException(String message) {
        Message = message;
    }

    @Override
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
