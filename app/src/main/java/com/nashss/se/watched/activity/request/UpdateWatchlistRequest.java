package com.nashss.se.watched.activity.request;


/**
 * Request class for updating a watchlist.
 */
public class UpdateWatchlistRequest {
    private final String id;
    private final String title;

    private UpdateWatchlistRequest(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static class Builder {
        private String id;
        private String title;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public UpdateWatchlistRequest build() {
            return new UpdateWatchlistRequest(id, title);
        }
    }
}






