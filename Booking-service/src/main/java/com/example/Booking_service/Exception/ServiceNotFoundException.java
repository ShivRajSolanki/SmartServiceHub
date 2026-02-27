package com.example.Booking_service.Exception;

public class ServiceNotFoundException extends  RuntimeException {

    public ServiceNotFoundException(String message) {
        super(message);
    }
}
