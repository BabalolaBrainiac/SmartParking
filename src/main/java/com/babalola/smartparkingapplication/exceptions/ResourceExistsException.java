package com.babalola.smartparkingapplication.exceptions;

public class ResourceExistsException extends RuntimeException {
    public ResourceExistsException(String message) {
        super(message);
    }
}