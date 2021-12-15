package com.example.atmserver.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NumberOfFailedAttemptExceedException extends RuntimeException {
}
