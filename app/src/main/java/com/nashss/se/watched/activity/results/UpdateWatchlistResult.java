package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.models.WatchlistModel;


/**
 * Result class for updating a watchlist.
 */
public class UpdateWatchlistResult {
    private final WatchlistModel watchlist;

    /**
     * Constructs an UpdateWatchlistResult object with the provided watchlist.
     *
     * @param watchlist the updated watchlist
     */
    private UpdateWatchlistResult(WatchlistModel watchlist) {
        this.watchlist = watchlist;
    }

    /**
     * Returns a new builder to construct an UpdateWatchlistResult.
     *
     * @return a new UpdateWatchlistResult builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Retrieves the updated watchlist.
     *
     * @return the updated watchlist
     */
    public WatchlistModel getWatchlist() {
        return watchlist;
    }

    /**
     * Builder class for constructing UpdateWatchlistResult objects.
     */
    public static class Builder {
        private WatchlistModel watchlist;

        private Builder() {
        }

        /**
         * Sets the updated watchlist for the result being built.
         *
         * @param watchlist the updated watchlist
         * @return the builder instance for method chaining
         */
        public Builder withWatchlist(WatchlistModel watchlist) {
            this.watchlist = watchlist;
            return this;
        }

        /**
         * Constructs an UpdateWatchlistResult based on the set parameters.
         *
         * @return the constructed UpdateWatchlistResult
         */
        public UpdateWatchlistResult build() {
            return new UpdateWatchlistResult(watchlist);
        }
    }
}

