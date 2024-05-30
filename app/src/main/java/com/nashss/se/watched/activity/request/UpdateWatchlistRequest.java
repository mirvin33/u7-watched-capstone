package com.nashss.se.watched.activity.request;


/**
 * Request class for updating a watchlist.
 */
public class UpdateWatchlistRequest {
    private final String id;
    private final String title;

    /**
     * Constructs an instance of UpdateWatchlistRequest.
     *
     * @param id    The ID of the watchlist to update.
     * @param title The new title for the watchlist.
     */
    private UpdateWatchlistRequest(String id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Creates a new Builder instance to build UpdateWatchlistRequest objects.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the ID of the watchlist to update.
     *
     * @return The ID of the watchlist to update.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the new title for the watchlist.
     *
     * @return The new title for the watchlist.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Builder class for constructing UpdateWatchlistRequest objects.
     */
    public static class Builder {
        private String id;
        private String title;

        /**
         * Sets the ID of the watchlist to update.
         *
         * @param id The ID of the watchlist to update.
         * @return This Builder instance.
         */
        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the new title for the watchlist.
         *
         * @param title The new title for the watchlist.
         * @return This Builder instance.
         */
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Builds an UpdateWatchlistRequest object with the provided parameters.
         *
         * @return An UpdateWatchlistRequest object.
         */
        public UpdateWatchlistRequest build() {
            return new UpdateWatchlistRequest(id, title);
        }
    }
}

