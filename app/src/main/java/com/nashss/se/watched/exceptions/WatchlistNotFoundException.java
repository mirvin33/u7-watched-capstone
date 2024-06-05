package com.nashss.se.watched.exceptions;

/**
 * Exception thrown when a watchlist is not found.
 */
public class WatchlistNotFoundException extends RuntimeException {
    /**
     * Constructs a new WatchlistNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public WatchlistNotFoundException(String message) {
        super(message);
    }
}

