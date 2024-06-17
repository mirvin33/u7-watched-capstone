package com.nashss.se.watched.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Request class for updating a watchlist.
 */
@JsonDeserialize(builder = UpdateWatchlistRequest.Builder.class)
public class UpdateWatchlistRequest {
    private final String id;
    private final String title;

    /**
     * Constructs an instance of UpdateWatchlistRequest.
     *
     * @param id    The ID of the watchlist to update.
     * @param title The new title for the watchlist.
     */
    public UpdateWatchlistRequest(String id, String title) {
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
    @JsonPOJOBuilder
    public static class Builder {
        private String id;
        private String title;
        private String userId;

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
         * Builds an UpdateWatchlistRequest object with the provided parameters.
         *
         * @return An UpdateWatchlistRequest object.
         */
        public UpdateWatchlistRequest build() {
            return new UpdateWatchlistRequest(id, title);
        }

    }
}

