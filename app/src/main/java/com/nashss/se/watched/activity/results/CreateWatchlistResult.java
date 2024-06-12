package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.dynamodb.models.Watchlist;

/**
 * Result class for creating a new watchlist.
 */
public class CreateWatchlistResult {
    private final Watchlist watchlist;

    /**
     * Constructs a CreateWatchlistResult object with the provided watchlist.
     *
     * @param watchlist the watchlist to include in the result
     */
    public CreateWatchlistResult(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    /**
     * Returns a new builder to construct a CreateWatchlistResult.
     *
     * @return a new CreateWatchlistResult builder
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
     * Builder class for constructing CreateWatchlistResult objects.
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
         * Constructs a CreateWatchlistResult based on the set parameters.
         *
         * @return the constructed CreateWatchlistResult
         */
        public CreateWatchlistResult build() {
            return new CreateWatchlistResult(watchlist);
        }
    }
}

