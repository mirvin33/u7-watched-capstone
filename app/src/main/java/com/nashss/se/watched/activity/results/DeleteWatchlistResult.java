package com.nashss.se.watched.activity.results;

/**
 * Result class for deleting a watchlist.
 */
public class DeleteWatchlistResult {
    private final String message;

    /**
     * Constructs a DeleteWatchlistResult object with the provided message.
     *
     * @param message the message associated with the deletion result
     */
    private DeleteWatchlistResult(String message) {
        this.message = message;
    }

    /**
     * Returns a new builder to construct a DeleteWatchlistResult.
     *
     * @return a new DeleteWatchlistResult builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Retrieves the message associated with the deletion result.
     *
     * @return the message indicating the result of the deletion operation
     */
    public String getMessage() {
        return message;
    }

    /**
     * Builder class for constructing DeleteWatchlistResult objects.
     */
    public static class Builder {
        private String message;

        /**
         * Sets the message for the deletion result being built.
         *
         * @param message the message to include in the result
         * @return the builder instance for method chaining
         */
        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Constructs a DeleteWatchlistResult based on the set parameters.
         *
         * @return the constructed DeleteWatchlistResult
         */
        public DeleteWatchlistResult build() {
            return new DeleteWatchlistResult(message);
        }
    }
}

