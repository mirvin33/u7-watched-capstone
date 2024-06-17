package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.models.WatchlistModel;

/**
 * Result class for creating a new watchlist.
 */
public class CreateWatchlistResult {
    private final WatchlistModel watchlist;

    /**
     * Constructs a CreateWatchlistResult object with the provided watchlist.
     *
     * @param watchlist the watchlist to include in the result
     */
    private CreateWatchlistResult(WatchlistModel watchlist) {
        this.watchlist = watchlist;
    }

    /**
     * Retrieves the watchlist included in this result.
     *
     * @return the watchlist associated with this result
     */

    public WatchlistModel getWatchlist() {
        return watchlist;
    }
    @Override
    public String toString() {
        return "CreateResult{" + "CreateResult='" + watchlist + '\'' + '}';
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
     * Builder class for constructing CreateWatchlistResult objects.
     */
    public static class Builder {
        private WatchlistModel watchlist;

        /**
         * Sets the watchlist for the result being built.
         *
         * @param watchlist the watchlist to include in the result
         * @return the builder instance for method chaining
         */
        public Builder withWatchlist(WatchlistModel watchlist) {
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

