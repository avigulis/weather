package org.assignment.weather.api;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.assignment.weather.application.ServiceUnavailable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    ApiError handleConstraintValidationException(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                                .map(this::toError)
                                .collect(Collectors.toList());
        return ApiError.builder()
                .errors(errors)
                .build();
    }

    @ExceptionHandler(ServiceUnavailable.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    @ResponseBody
    ApiError handleServiceUnavailable(ServiceUnavailable ex) {
        log.error(ex.getMessage(), ex);
        return ApiError.builder()
                .error("service temporary unavailable")
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    ApiError handleAllExceptions(Exception ex) {
        log.error("unpredictable error :", ex);
        return ApiError.builder()
                .error("unpredictable error :(")
                .build();
    }

    private String toError(ConstraintViolation<?> cv) {
        return format("%s %s", cv.getPropertyPath().toString(), cv.getMessage());
    }
}
