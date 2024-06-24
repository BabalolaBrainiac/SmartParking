package com.babalola.smartparkingapplication.models;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private String message;
    private int statusCode;

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
