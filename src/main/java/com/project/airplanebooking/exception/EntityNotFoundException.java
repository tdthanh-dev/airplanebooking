package com.project.airplanebooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super(String.format("%s with ID %d not found", entityClass.getSimpleName(), id));
    }

    public EntityNotFoundException(Class<?> entityClass, String fieldName, Object fieldValue) {
        super(String.format("%s with %s '%s' not found", entityClass.getSimpleName(), fieldName, fieldValue));
    }
}