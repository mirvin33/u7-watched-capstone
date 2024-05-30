package com.nashss.se.watched.activity.request;

import java.util.List;

/**
 * Request class for creating a new watchlist.
 */
public class CreateWatchlistRequest {
    private final String title; // The title of the new watchlist.
    private final String userId; // The ID of the user creating the watchlist.

    /**
     * Constructs an instance of CreateWatchlistRequest.
     *
     * @param title The title of the new watchlist.
     * @param userId The ID of the user creating the watchlist.
     */
    private CreateWatchlistRequest(String title, String userId) {
        this.title = title;
        this.userId = userId;
    }

    /**
     * Creates a new Builder instance to build CreateWatchlistRequest objects.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the title of the new watchlist.
     *
     * @return The title of the new watchlist.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the ID of the user creating the watchlist.
     *
     * @return The ID of the user creating the watchlist.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Builder class for constructing CreateWatchlistRequest objects.
     */
    public static class Builder {
        private String title; // The title of the new watchlist.
        private String userId; // The ID of the user creating the watchlist.

        /**
         * Sets the title of the new watchlist.
         *
         * @param title The title of the new watchlist.
         * @return This Builder instance.
         */
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the ID of the user creating the watchlist.
         *
         * @param userId The ID of the user creating the watchlist.
         * @return This Builder instance.
         */
        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Builds a CreateWatchlistRequest object with the provided parameters.
         *
         * @return A CreateWatchlistRequest object.
         */
        public CreateWatchlistRequest build() {
            return new CreateWatchlistRequest(title, userId);
        }
    }
}

