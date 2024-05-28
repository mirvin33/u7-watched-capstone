package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.models.WatchlistModel;

/**
 * Represents the result of a get watchlist request.
 */
public class GetWatchlistResult {
    private final WatchlistModel watchlist;

    /**
     * Constructs a GetWatchlistResult with the specified watchlist.
     *
     * @param builder the builder containing the watchlist
     */
    public GetWatchlistResult(Builder builder) {
        this.watchlist = builder.watchlist;
    }

    /**
     * Returns the retrieved watchlist.
     *
     * @return the watchlist
     */
    public WatchlistModel getWatchlist() {
        return watchlist;
    }

    /**
     * Returns a new Builder for creating a GetWatchlistResult.
     *
     * @return a new Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for creating a GetWatchlistResult.
     */
    public static class Builder {
        private WatchlistModel watchlist;

        /**
         * Sets the watchlist for the result.
         *
         * @param watchlist the watchlist
         * @return this builder
         */
        public Builder withWatchlist(WatchlistModel watchlist) {
            this.watchlist = watchlist;
            return this;
        }

        /**
         * Builds and returns the GetWatchlistResult.
         *
         * @return the created GetWatchlistResult
         */
        public GetWatchlistResult build() {
            return new GetWatchlistResult(this);
        }
    }
}

