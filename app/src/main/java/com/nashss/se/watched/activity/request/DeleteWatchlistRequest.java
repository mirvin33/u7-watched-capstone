package com.nashss.se.watched.activity.request;

/**
 * Request class for deleting a watchlist.
 */
public class DeleteWatchlistRequest {
    private final String id;

    private DeleteWatchlistRequest(String id) {
        this.id = id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public static class Builder {
        private String id;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public DeleteWatchlistRequest build() {
            return new DeleteWatchlistRequest(id);
        }
    }
}
