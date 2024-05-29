package com.nashss.se.watched.activity.request;

import java.util.List;

/**
 * Request class for creating a new watchlist.
 */
public class CreateWatchlistRequest {
    private final String title;
    private final String userId;

    private CreateWatchlistRequest(String title, String userId) {
        this.title = title;
        this.userId = userId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public static class Builder {
        private String title;
        private String userId;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public CreateWatchlistRequest build() {
            return new CreateWatchlistRequest(title, userId);
        }
    }
}