package com.test.flights.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ApiError {

    private HttpStatus httpStatus;

    private String message;

    private List<String> messages;

    public ApiError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ApiError(HttpStatus httpStatus, List<String> messages) {
        this.httpStatus = httpStatus;
        this.messages = messages;
    }
}
