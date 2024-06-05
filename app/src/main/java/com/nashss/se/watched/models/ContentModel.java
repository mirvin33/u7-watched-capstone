package com.nashss.se.watched.models;

import java.util.Objects;
import java.util.UUID;

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
        this.contentId = contentId != null ? contentId : UUID.randomUUID().toString();
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

    @Override
    public int hashCode() {
        return Objects.hash(contentId, watchlistId, title, userId, streamService, watched);
    }

    /**
     * Returns a new builder instance to construct a ContentModel object.
     *
     * @return A new instance of the ContentModel builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern for constructing ContentModel instances.
     * Provides methods to set individual attributes of the ContentModel.
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
         * @param pContentId The unique identifier for the content.
         * @return The builder instance for method chaining.
         */
        public Builder withContentId(String pContentId) {
            this.contentId = pContentId;
            return this;
        }

        /**
         * Sets the watchlistId for the ContentModel being built.
         *
         * @param pWatchlistId The identifier for the watchlist containing the content.
         * @return The builder instance for method chaining.
         */
        public Builder withWatchlistId(String pWatchlistId) {
            this.watchlistId = pWatchlistId;
            return this;
        }

        /**
         * Sets the title for the ContentModel being built.
         *
         * @param pTitle The title of the content.
         * @return The builder instance for method chaining.
         */
        public Builder withTitle(String pTitle) {
            this.title = pTitle;
            return this;
        }

        /**
         * Sets the userId for the ContentModel being built.
         *
         * @param pUserId The identifier of the user associated with the content.
         * @return The builder instance for method chaining.
         */
        public Builder withUserId(String pUserId) {
            this.userId = pUserId;
            return this;
        }

        /**
         *
         * @param pStreamService The streaming service for the content.
         * @return The builder instance for method chaining.
         */
        public Builder withStreamService(String pStreamService) {
            this.streamService = pStreamService;
            return this;
        }

        /**
         *
         * @param pWatched Boolean for if show has been watched.
         * @return The builder instance for method chaining.
         */
        public Builder withWatched(Boolean pWatched) {
            this.watched = pWatched;
            return this;
        }

        /**
         * Constructs a ContentModel object based on the set parameters.
         *
         * @return The constructed ContentModel instance.
         */
        public ContentModel build() {
            return new ContentModel(contentId, watchlistId, title, userId, streamService, watched);
        }
    }
}

