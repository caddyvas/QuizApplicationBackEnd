package com.deepak.quizapplication.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private String errorCode;
    private String message;
    private HttpStatus httpStatus;
}
