package com.nashss.se.watched.activity.results;

import com.nashss.se.watched.dynamodb.models.Watchlist;

import java.util.List;

/**
 * Result class for retrieving watchlists for a user.
 */
public class GetWatchlistsForUserResult {
    private final List<Watchlist> watchlists;

    private GetWatchlistsForUserResult(List<Watchlist> watchlists) {
        this.watchlists = watchlists;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Watchlist> getWatchlists() {
        return watchlists;
    }

    public static class Builder {
        private List<Watchlist> watchlists;

        public Builder withWatchlists(List<Watchlist> watchlists) {
            this.watchlists = watchlists;
            return this;
        }

        public GetWatchlistsForUserResult build() {
            return new GetWatchlistsForUserResult(watchlists);
        }
    }
}