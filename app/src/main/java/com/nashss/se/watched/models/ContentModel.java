package com.nashss.se.watched.models;

import java.util.Objects;

/**
 * POJO for content.
 */
public class ContentModel {
    private final String contentId;
    private final String watchlistId;
    private final String title;
    private final String userId;
    private final String streamService;
    private final Boolean watched;

    /**
     * @param contentId     the unique identifier for the content.
     * @param watchlistId   the identifier for the watchlist containing this content
     * @param title         title of the show or movie.
     * @param userId        the identifier of the user (email).
     * @param streamService the streaming service where the content is available.
     * @param watched       indicates whether the content has been watched.
     */
    public ContentModel(String contentId, String watchlistId, String title, String userId, String streamService,
                        Boolean watched) {
        this.contentId = contentId;
        this.watchlistId = watchlistId;
        this.title = title;
        this.userId = userId;
        this.streamService = streamService;
        this.watched = watched;
    }

    public String getContentId() {
        return contentId;
    }

    public String getWatchlistId() {
        return watchlistId;
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public String getStreamService() {
        return streamService;
    }

    public Boolean getWatched() {
        return watched;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContentModel that = (ContentModel) o;
        return Objects.equals(contentId, that.contentId) &&
                Objects.equals(watchlistId, that.watchlistId) && Objects.equals(title, that.title) &&
                Objects.equals(userId, that.userId) && Objects.equals(streamService, that.streamService) &&
                Objects.equals(watched, that.watched);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(contentId, watchlistId, title, userId, streamService, watched);
    }

    /**
     * Creates a builder for ContentModel.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for ContentModel.
     */
    public static class Builder {
        private String contentId;
        private String watchlistId;
        private String title;
        private String userId;
        private String streamService;
        private Boolean watched;

        /**
         * Sets the contentId for the ContentModel being built.
         *
         * @param pContentId the unique identifier for the content
         * @return the current Builder instance
         */
        public Builder withContentId(String pContentId) {
            this.contentId = pContentId;
            return this;
        }

        /**
         * Sets the watchlistId for the ContentModel being built.
         *
         * @param pWatchlistId the identifier for the watchlist containing this content
         * @return the current Builder instance
         */
        public Builder withWatchlistId(String pWatchlistId) {
            this.watchlistId = pWatchlistId;
            return this;
        }

        /**
         * Sets the title for the ContentModel being built.
         *
         * @param pTitle the title of the show or movie
         * @return the current Builder instance
         */
        public Builder withTitle(String pTitle) {
            this.title = pTitle;
            return this;
        }

        /**
         * Sets the userId for the ContentModel being built.
         *
         * @param pUserId the identifier of the user (usually an email)
         * @return the current Builder instance
         */
        public Builder withUserId(String pUserId) {
            this.userId = pUserId;
            return this;
        }

        /**
         * Sets the streamService for the ContentModel being built.
         *
         * @param pStreamService the streaming service where the content is available
         * @return the current Builder instance
         */
        public Builder withStreamService(String pStreamService) {
            this.streamService = pStreamService;
            return this;
        }

        /**
         * Sets the watched status for the ContentModel being built.
         *
         * @param pWatched whether the content has been watched
         * @return the current Builder instance
         */
        public Builder withWatched(Boolean pWatched) {
            this.watched = pWatched;
            return this;
        }

        /**
         * Builds and returns a ContentModel instance.
         *
         * @return a new ContentModel instance
         */
        public ContentModel build() {
            return new ContentModel(contentId, watchlistId, title, userId, streamService, watched);
        }
    }
}

