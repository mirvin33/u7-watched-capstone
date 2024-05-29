package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.dynamodb.models.Watchlist;

/**
 * Result class for adding content to a watchlist.
 */
public class AddContentToWatchlistResult {
    private final Watchlist watchlist;

    private AddContentToWatchlistResult(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public static class Builder {
        private Watchlist watchlist;

        public Builder withWatchlist(Watchlist watchlist) {
            this.watchlist = watchlist;
            return this;
        }

        public AddContentToWatchlistResult build() {
            return new AddContentToWatchlistResult(watchlist);
        }
    }
}