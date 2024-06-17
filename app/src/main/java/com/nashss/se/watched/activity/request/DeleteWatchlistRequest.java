package com.nashss.se.watched.activity.request;

/**
 * Request class for deleting a watchlist.
 */
public class DeleteWatchlistRequest {
    private final String id;
    private final String userId;

    /**
     * Constructs an instance of DeleteWatchlistRequest.
     *
     * @param id The ID of the watchlist to be deleted.
     * @param userId the email of the user.
     */
    public DeleteWatchlistRequest(String id, String userId) {
        this.id = id;
        this.userId = userId;
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
        private String userId;

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
         * Sets the userId for the watchlist.
         *
         * @param userId email of the user.
         * @return This Builder instance.
         */
        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
        /**
         * Builds a DeleteWatchlistRequest object with the provided parameters.
         *
         * @return A DeleteWatchlistRequest object.
         */
        public DeleteWatchlistRequest build() {
            return new DeleteWatchlistRequest(id, userId);

        }
    }
}

