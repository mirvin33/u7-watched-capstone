package com.nashss.se.watched.activity.results;

/**
 * Result class for deleting a watchlist.
 */
public class DeleteWatchlistResult {
    private final String deleteResult;

    /**
     * Constructs a DeleteWatchlistResult object with the provided message.
     *
     * @param deleteResult associated with the deletion result
     */
    private DeleteWatchlistResult(String deleteResult) {
        this.deleteResult = deleteResult;
    }
    /**
     * Retrieves the message associated with the deletion result.
     *
     * @return the message indicating the result of the deletion operation
     */

    public String getDeleteResult() {
        return deleteResult;
    }

    @Override
    public String toString() {
        return "DeleteResult{" + "deleteResult='" + deleteResult + '\'' + '}';
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
     * Builder class for constructing DeleteWatchlistResult objects.
     */
    public static class Builder {
        private String deleteResult;

        /**
         * Sets the message for the deletion result being built.
         *
         * @param deleteResult the result
         * @return the builder instance for method chaining
         */
        public Builder withDeleteResult(String deleteResult) {
            this.deleteResult = deleteResult;
            return this;
        }

        /**
         * Constructs a DeleteWatchlistResult based on the set parameters.
         *
         * @return the constructed DeleteWatchlistResult
         */
        public DeleteWatchlistResult build() {
            return new DeleteWatchlistResult(deleteResult);
        }
    }
}

