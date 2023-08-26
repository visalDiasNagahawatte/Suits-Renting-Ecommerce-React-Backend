package com.example.ecommerceapi.exception;

public class RestrictedAreaException extends RuntimeException {
    public RestrictedAreaException(String message) {
        super(message);
    }
}
