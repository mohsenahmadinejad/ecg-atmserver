package com.example.atmserver.demo.controller.advice;

import com.example.atmserver.demo.dto.MessageDTO;
import com.example.atmserver.demo.exception.CardNotFoundException;
import com.example.atmserver.demo.exception.InvalidUsernameAndPasswordException;
import com.example.atmserver.demo.exception.NotEnoughBalanceException;
import com.example.atmserver.demo.exception.NumberOfFailedAttemptExceedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AtmAdvice {

    @ExceptionHandler(NotEnoughBalanceException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public MessageDTO processNotEnoughBalanceException(NotEnoughBalanceException exception) {

        MessageDTO message = new MessageDTO();
        message.setMessage(exception.getMessage());
        message.setType("ERROR");
        return message;
    }

    @ExceptionHandler(CardNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDTO processCardNotFoundException(CardNotFoundException exception) {

        MessageDTO message = new MessageDTO();
        message.setMessage("Card Number not found");
        message.setType("ERROR");
        return message;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDTO processIllegalArgumentException(IllegalArgumentException exception) {

        MessageDTO message = new MessageDTO();
        message.setMessage(exception.getMessage());
        message.setType("ERROR");
        return message;
    }

    @ExceptionHandler(NumberOfFailedAttemptExceedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MessageDTO processNumberOfFailedAttemptExceedException(NumberOfFailedAttemptExceedException exception) {
        MessageDTO message = new MessageDTO();
        message.setMessage("Number Of Failed Attempt Exceed than 3");
        message.setType("ERROR");
        return message;
    }

    @ExceptionHandler(InvalidUsernameAndPasswordException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MessageDTO processInvalidUsernameAndPasswordException(InvalidUsernameAndPasswordException exception) {
        MessageDTO message = new MessageDTO();
        message.setMessage("Invalid Username And Password Exception");
        message.setType("ERROR");
        return message;
    }
}
