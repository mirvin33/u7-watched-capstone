package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.dynamodb.models.Watchlist;

/**
 * Result class for retrieving watchlist content.
 */
public class GetWatchlistContentResult {
    private final Watchlist watchlist;

    /**
     * Constructs a GetWatchlistContentResult object with the provided watchlist.
     *
     * @param watchlist the retrieved watchlist content
     */
    private GetWatchlistContentResult(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    /**
     * Returns a new builder to construct a GetWatchlistContentResult.
     *
     * @return a new GetWatchlistContentResult builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Retrieves the watchlist content.
     *
     * @return the watchlist content
     */
    public Watchlist getWatchlist() {
        return watchlist;
    }

    /**
     * Builder class for constructing GetWatchlistContentResult objects.
     */
    public static class Builder {
        private Watchlist watchlist;

        /**
         * Sets the watchlist content for the result being built.
         *
         * @param watchlist the watchlist content to include in the result
         * @return the builder instance for method chaining
         */
        public Builder withWatchlist(Watchlist watchlist) {
            this.watchlist = watchlist;
            return this;
        }

        /**
         * Constructs a GetWatchlistContentResult based on the set parameters.
         *
         * @return the constructed GetWatchlistContentResult
         */
        public GetWatchlistContentResult build() {
            return new GetWatchlistContentResult(watchlist);
        }
    }
}

