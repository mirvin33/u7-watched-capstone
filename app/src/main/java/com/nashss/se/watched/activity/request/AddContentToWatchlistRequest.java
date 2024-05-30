package com.nashss.se.watched.activity.request;

/**
 * Request class for adding content to a watchlist.
 */
public class AddContentToWatchlistRequest {
    private final String id;
    private final String contentId;
    private final boolean queueNext;

    /**
     * Constructs an instance of AddContentToWatchlistRequest.
     *
     * @param id The ID of the user or session making the request.
     * @param contentId The ID of the content being added to the watchlist.
     * @param queueNext Indicates whether the content should be queued next for playback.
     */
    private AddContentToWatchlistRequest(String id, String contentId, boolean queueNext) {
        this.id = id;
        this.contentId = contentId;
        this.queueNext = queueNext;
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
     * Checks whether the content should be queued next for playback.
     *
     * @return true if the content should be queued next, false otherwise.
     */
    public boolean getQueueNext() {
        return queueNext;
    }

    /**
     * Builder class for constructing AddContentToWatchlistRequest objects.
     */
    public static class Builder {
        private String id;
        private String contentId;
        private boolean queueNext;

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
         * Sets whether the content should be queued next for playback.
         *
         * @param queueNext true if the content should be queued next, false otherwise.
         * @return This Builder instance.
         */
        public Builder withQueueNext(boolean queueNext) {
            this.queueNext = queueNext;
            return this;
        }

        /**
         * Builds an AddContentToWatchlistRequest object with the provided parameters.
         *
         * @return An AddContentToWatchlistRequest object.
         */
        public AddContentToWatchlistRequest build() {
            return new AddContentToWatchlistRequest(id, contentId, queueNext);
        }
    }
}

