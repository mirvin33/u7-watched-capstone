package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.dynamodb.models.Watchlist;

/**
 * Result class for updating a watchlist.
 */
public class UpdateWatchlistResult {
    private final Watchlist watchlist;

    private UpdateWatchlistResult(Watchlist watchlist) {
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

        public UpdateWatchlistResult build() {
            return new UpdateWatchlistResult(watchlist);
        }
    }
}

