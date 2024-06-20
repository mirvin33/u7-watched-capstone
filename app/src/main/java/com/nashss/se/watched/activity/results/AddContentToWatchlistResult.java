package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.dynamodb.models.Watchlist;

/**
 * Result class for adding content to a watchlist.
 */
public class AddContentToWatchlistResult {
    private final Watchlist watchlist;
    private final String contentId;
    private final String title;
    private final String streamService;
    private final Boolean watched;

    /**
     * Constructs an AddContentToWatchlistResult object with the provided watchlist and content details.
     *
     * @param watchlist the watchlist to include in the result
     * @param contentId the ID of the content added
     * @param title the title of the content added
     * @param streamService the streaming service of the content added
     * @param watched whether the content has been watched
     */
    private AddContentToWatchlistResult(Watchlist watchlist, String contentId, String title, String streamService, Boolean watched) {
        this.watchlist = watchlist;
        this.contentId = contentId;
        this.title = title;
        this.streamService = streamService;
        this.watched = watched;
    }

    /**
     * Returns a new builder to construct an AddContentToWatchlistResult.
     *
     * @return a new AddContentToWatchlistResult builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Retrieves the watchlist included in this result.
     *
     * @return the watchlist associated with this result
     */
    public Watchlist getWatchlist() {
        return watchlist;
    }

    /**
     * Retrieves the content ID included in this result.
     *
     * @return the content ID associated with this result
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * Retrieves the title included in this result.
     *
     * @return the title associated with this result
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the streaming service included in this result.
     *
     * @return the streaming service associated with this result
     */
    public String getStreamService() {
        return streamService;
    }

    /**
     * Retrieves the watched status included in this result.
     *
     * @return the watched status associated with this result
     */
    public Boolean getWatched() {
        return watched;
    }

    /**
     * Builder class for constructing AddContentToWatchlistResult objects.
     */
    public static class Builder {
        private Watchlist watchlist;
        private String contentId;
        private String title;
        private String streamService;
        private Boolean watched;

        /**
         * Sets the watchlist for the result being built.
         *
         * @param watchlist the watchlist to include in the result
         * @return the builder instance for method chaining
         */
        public Builder withWatchlist(Watchlist watchlist) {
            this.watchlist = watchlist;
            return this;
        }

        /**
         * Sets the content ID for the result being built.
         *
         * @param contentId the content ID to include in the result
         * @return the builder instance for method chaining
         */
        public Builder withContentId(String contentId) {
            this.contentId = contentId;
            return this;
        }

        /**
         * Sets the title for the result being built.
         *
         * @param title the title to include in the result
         * @return the builder instance for method chaining
         */
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the streaming service for the result being built.
         *
         * @param streamService the streaming service to include in the result
         * @return the builder instance for method chaining
         */
        public Builder withStreamService(String streamService) {
            this.streamService = streamService;
            return this;
        }

        /**
         * Sets the watched status for the result being built.
         *
         * @param watched the watched status to include in the result
         * @return the builder instance for method chaining
         */
        public Builder withWatched(Boolean watched) {
            this.watched = watched;
            return this;
        }

        /**
         * Constructs an AddContentToWatchlistResult based on the set parameters.
         *
         * @return the constructed AddContentToWatchlistResult
         */
        public AddContentToWatchlistResult build() {
            return new AddContentToWatchlistResult(watchlist, contentId, title, streamService, watched);
        }
    }
}

