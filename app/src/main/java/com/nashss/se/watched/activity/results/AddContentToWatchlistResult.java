package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.dynamodb.models.Watchlist;

/**
 * Result class for adding content to a watchlist.
 */
public class AddContentToWatchlistResult {
    private final Watchlist watchlist;

    /**
     * Constructs an AddContentToWatchlistResult object with the provided watchlist.
     *
     * @param watchlist the watchlist to include in the result
     */
    private AddContentToWatchlistResult(Watchlist watchlist) {
        this.watchlist = watchlist;
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
     * Builder class for constructing AddContentToWatchlistResult objects.
     */
    public static class Builder {
        private Watchlist watchlist;

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
         * Constructs an AddContentToWatchlistResult based on the set parameters.
         *
         * @return the constructed AddContentToWatchlistResult
         */
        public AddContentToWatchlistResult build() {
            return new AddContentToWatchlistResult(watchlist);
        }
    }
}

