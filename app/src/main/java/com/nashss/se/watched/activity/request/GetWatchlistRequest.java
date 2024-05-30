package com.nashss.se.watched.activity.request;



/**
 * Represents a request to get a watchlist by ID.
 */
public class GetWatchlistRequest {
    private final String id;

    /**
     * Constructs a GetWatchlistRequest with the specified ID.
     *
     * @param builder the builder containing the ID
     */
    public GetWatchlistRequest(Builder builder) {
        this.id = builder.id;
    }

    /**
     * Returns the ID of the watchlist to retrieve.
     *
     * @return the ID of the watchlist
     */
    public String getId() {
        return id;
    }

    /**
     * Returns a new Builder for creating a GetWatchlistRequest.
     *
     * @return a new Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for creating a GetWatchlistRequest.
     */
    public static class Builder {
        private String id;

        /**
         * Sets the ID of the watchlist to retrieve.
         *
         * @param id the ID of the watchlist
         * @return this builder
         */
        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        /**
         * Builds and returns the GetWatchlistRequest.
         *
         * @return the created GetWatchlistRequest
         */
        public GetWatchlistRequest build() {
            return new GetWatchlistRequest(this);
        }
    }
}


