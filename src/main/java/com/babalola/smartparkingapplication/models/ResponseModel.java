package com.babalola.smartparkingapplication.models;


import lombok.Data;

@Data
public class ResponseModel<T> {
    private String message;
    private T data;
    private int statusCode;

    public ResponseModel(String message, T data, int statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }
}
