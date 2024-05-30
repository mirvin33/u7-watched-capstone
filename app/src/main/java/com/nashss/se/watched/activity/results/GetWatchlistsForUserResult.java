package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.dynamodb.models.Watchlist;

import java.util.List;

/**
 * Result class for retrieving watchlists for a user.
 */
public class GetWatchlistsForUserResult {
    private final List<Watchlist> watchlists;

    /**
     * Constructs a GetWatchlistsForUserResult object with the provided list of watchlists.
     *
     * @param watchlists the list of watchlists retrieved for the user
     */
    private GetWatchlistsForUserResult(List<Watchlist> watchlists) {
        this.watchlists = watchlists;
    }

    /**
     * Returns a new builder to construct a GetWatchlistsForUserResult.
     *
     * @return a new GetWatchlistsForUserResult builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Retrieves the list of watchlists for the user.
     *
     * @return the list of watchlists
     */
    public List<Watchlist> getWatchlists() {
        return watchlists;
    }

    /**
     * Builder class for constructing GetWatchlistsForUserResult objects.
     */
    public static class Builder {
        private List<Watchlist> watchlists;

        /**
         * Sets the list of watchlists for the result being built.
         *
         * @param watchlists the list of watchlists to include in the result
         * @return the builder instance for method chaining
         */
        public Builder withWatchlists(List<Watchlist> watchlists) {
            this.watchlists = watchlists;
            return this;
        }

        /**
         * Constructs a GetWatchlistsForUserResult based on the set parameters.
         *
         * @return the constructed GetWatchlistsForUserResult
         */
        public GetWatchlistsForUserResult build() {
            return new GetWatchlistsForUserResult(watchlists);
        }
    }
}

