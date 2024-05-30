package com.nashss.se.watched.activity.results;

/**
 * Result class for deleting a watchlist.
 */
public class DeleteWatchlistResult {
    private final String message;

    private DeleteWatchlistResult(String message) {
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {
        private String message;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public DeleteWatchlistResult build() {
            return new DeleteWatchlistResult(message);
        }
    }
}

