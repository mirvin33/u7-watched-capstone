package com.nashss.se.watched.exceptions;

/**
 * Exception to throw when a provided value has invalid attribute values.
 */
public class InvalidAttributeValueException extends RuntimeException {
    public InvalidAttributeValueException(String message) {
        super(message);
    }
}

