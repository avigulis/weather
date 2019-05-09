package org.assignment.weather.application;

public class ServiceUnavailable extends RuntimeException {

    public ServiceUnavailable(String message, Throwable cause) {
        super(message, cause);
    }
}
