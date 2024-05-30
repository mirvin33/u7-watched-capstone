package com.nashss.se.watched.activity.request;

/**
 * Request class for deleting a watchlist.
 */
public class DeleteWatchlistRequest {
    private final String id;

    /**
     * Constructs an instance of DeleteWatchlistRequest.
     *
     * @param id The ID of the watchlist to be deleted.
     */
    private DeleteWatchlistRequest(String id) {
        this.id = id;
    }

    /**
     * Creates a new Builder instance to build DeleteWatchlistRequest objects.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the ID of the watchlist to be deleted.
     *
     * @return The ID of the watchlist to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Builder class for constructing DeleteWatchlistRequest objects.
     */
    public static class Builder {
        private String id;

        /**
         * Sets the ID of the watchlist to be deleted.
         *
         * @param id The ID of the watchlist to be deleted.
         * @return This Builder instance.
         */
        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        /**
         * Builds a DeleteWatchlistRequest object with the provided parameters.
         *
         * @return A DeleteWatchlistRequest object.
         */
        public DeleteWatchlistRequest build() {
            return new DeleteWatchlistRequest(id);
        }
    }
}

