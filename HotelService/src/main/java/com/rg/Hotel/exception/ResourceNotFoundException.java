package com.rg.Hotel.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceNotFound) {
        super(resourceNotFound);
    }
}
