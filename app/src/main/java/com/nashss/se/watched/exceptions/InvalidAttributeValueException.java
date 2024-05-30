package com.nashss.se.watched.exceptions;

/**
 * Exception to throw when a provided value has invalid attribute values.
 */
public class InvalidAttributeValueException extends RuntimeException {
    /**
     * Constructs an InvalidAttributeValueException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidAttributeValueException(String message) {
        super(message);
    }
}

