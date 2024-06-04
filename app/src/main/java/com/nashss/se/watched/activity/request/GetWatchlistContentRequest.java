package com.nashss.se.watched.activity.request;



/**
 * Request class for retrieving watchlist content.
 */
public class GetWatchlistContentRequest {
    private final String id;

    /**
     * Constructs an instance of GetWatchlistContentRequest.
     *
     * @param id The ID of the watchlist for which content is requested.
     */
    public GetWatchlistContentRequest(String id) {
        this.id = id;
    }

    /**
     * Creates a new Builder instance to build GetWatchlistContentRequest objects.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the ID of the watchlist for which content is requested.
     *
     * @return The ID of the watchlist for which content is requested.
     */
    public String getId() {
        return id;
    }

    /**
     * Builder class for constructing GetWatchlistContentRequest objects.
     */
    public static class Builder {
        private String id;

        /**
         * Sets the ID of the watchlist for which content is requested.
         *
         * @param id The ID of the watchlist for which content is requested.
         * @return This Builder instance.
         */
        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        /**
         * Builds a GetWatchlistContentRequest object with the provided parameters.
         *
         * @return A GetWatchlistContentRequest object.
         */
        public GetWatchlistContentRequest build() {
            return new GetWatchlistContentRequest(id);
        }
    }
}

