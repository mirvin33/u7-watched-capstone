package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.dynamodb.models.Watchlist;

/**
 * Result class for creating a new watchlist.
 */
public class CreateWatchlistResult {
    private final Watchlist watchlist;

    private CreateWatchlistResult(Watchlist watchlist) {
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

        public CreateWatchlistResult build() {
            return new CreateWatchlistResult(watchlist);
        }
    }
}


