package com.nashss.se.watched.exceptions;

/**
 * Exception to throw when a given contentId is not found
 * in the database.
 */
public class ContentNotFoundException extends RuntimeException {
    /**
     * Exception with a message, but no cause.
     *
     * @param message A descriptive message for this exception.
     */
    public ContentNotFoundException(String message) {
        super(message);
    }
}

