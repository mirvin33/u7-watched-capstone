package com.nashss.se.watched.activity.request;

/**
 * Request class for adding content to a watchlist.
 */
public class AddContentToWatchlistRequest {
    private final String id;
    private final String userId;
    private final String contentId;
    private final boolean watched;

    /**
     * Constructs an instance of AddContentToWatchlistRequest.
     *
     * @param id The ID of the user or session making the request.
     * @param contentId The ID of the content being added to the watchlist.
     * @param watched Indicates whether the content should be queued next for playback.
     */
    public AddContentToWatchlistRequest(String id, String contentId, String userId, boolean watched) {
        this.id = id;
        this.contentId = contentId;
        this.userId = userId;
        this.watched = watched;
    }

    /**
     * Creates a new Builder instance to build AddContentToWatchlistRequest objects.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the ID of the user or session making the request.
     *
     * @return The ID of the user or session making the request.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the ID of the content being added to the watchlist.
     *
     * @return The ID of the content being added to the watchlist.
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * Gets the userId for content being added to the watchlist.
     *
     * @return The ID of the content being added to the watchlist.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Checks whether the content should be queued next for playback.
     *
     * @return true if the content should be queued next, false otherwise.
     */
    public boolean getWatched() {
        return watched;
    }

    /**
     * Builder class for constructing AddContentToWatchlistRequest objects.
     */
    public static class Builder {
        private String id;
        private String contentId;
        private String userId;
        private boolean watched;

        /**
         * Sets the ID of the user or session making the request.
         *
         * @param id The ID of the user or session making the request.
         * @return This Builder instance.
         */
        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the ID of the content being added to the watchlist.
         *
         * @param contentId The ID of the content being added to the watchlist.
         * @return This Builder instance.
         */
        public Builder withContentId(String contentId) {
            this.contentId = contentId;
            return this;
        }

        /**
         * Sets the ID of the user or session making the request.
         *
         * @param userId The ID of the user or session making the request.
         * @return This Builder instance.
         */
        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Sets whether the content should be queued next for playback.
         *
         * @param watched true if the content should be queued next, false otherwise.
         * @return This Builder instance.
         */
        public Builder withWatched(boolean watched) {
            this.watched = watched;
            return this;
        }

        /**
         * Builds an AddContentToWatchlistRequest object with the provided parameters.
         *
         * @return An AddContentToWatchlistRequest object.
         */
        public AddContentToWatchlistRequest build() {
            return new AddContentToWatchlistRequest(id, contentId, userId, watched);
        }
    }
}

