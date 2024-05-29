package com.nashss.se.watched.activity.request;

/**
 * Request class for adding content to a watchlist.
 */
public class AddContentToWatchlistRequest {
    private final String id;
    private final String contentId;
    private final boolean queueNext;

    private AddContentToWatchlistRequest(String id, String contentId, boolean queueNext) {
        this.id = id;
        this.contentId = contentId;
        this.queueNext = queueNext;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getContentId() {
        return contentId;
    }

    public boolean getQueueNext() {
        return queueNext;
    }

    public static class Builder {
        private String id;
        private String contentId;
        private boolean queueNext;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withContentId(String contentId) {
            this.contentId = contentId;
            return this;
        }

        public Builder withQueueNext(boolean queueNext) {
            this.queueNext = queueNext;
            return this;
        }

        public AddContentToWatchlistRequest build() {
            return new AddContentToWatchlistRequest(id, contentId, queueNext);
        }
    }
}