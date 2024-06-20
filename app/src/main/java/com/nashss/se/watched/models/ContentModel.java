package com.nashss.se.watched.models;

import java.util.Objects;
import java.util.UUID;

/**
 * POJO for content.
 */
public class ContentModel {
    private final String contentId;
    private final String title;
    private final String streamService;
    private final Boolean watched;

    /**
     * @param contentId     the unique identifier for the content.
     * @param title         title of the show or movie.
     * @param streamService the streaming service where the content is available.
     * @param watched       indicates whether the content has been watched.
     */
    public ContentModel(String contentId, String title, String streamService,
                        Boolean watched) {
        this.contentId = contentId != null ? contentId : UUID.randomUUID().toString();
        this.title = title;
        this.streamService = streamService;
        this.watched = watched;
    }

    public String getContentId() {
        return contentId;
    }

    public String getTitle() {
        return title;
    }

    public String getStreamService() {
        return streamService;
    }

    public Boolean getWatched() {
        return watched;
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
        private String title;
        private String streamService;
        private Boolean watched;

        /**
         * Sets the contentId for the ContentModel being built.
         *
         * @param contentId The unique identifier for the content.
         * @return The builder instance for method chaining.
         */
        public Builder withContentId(String contentId) {
            this.contentId = contentId;
            return this;
        }

        /**
         * Sets the title for the ContentModel being built.
         *
         * @param title The title of the content.
         * @return The builder instance for method chaining.
         */
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         *
         * @param streamService The streaming service for the content.
         * @return The builder instance for method chaining.
         */
        public Builder withStreamService(String streamService) {
            this.streamService = streamService;
            return this;
        }

        /**
         *
         * @param watched Boolean for if show has been watched.
         * @return The builder instance for method chaining.
         */
        public Builder withWatched(Boolean watched) {
            this.watched = watched;
            return this;
        }

        /**
         * Constructs a ContentModel object based on the set parameters.
         *
         * @return The constructed ContentModel instance.
         */
        public ContentModel build() {
            return new ContentModel(contentId, title, streamService, watched);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentModel that = (ContentModel) o;
        return Objects.equals(contentId, that.contentId) && Objects.equals(title, that.title) && Objects.equals(streamService, that.streamService) && Objects.equals(watched, that.watched);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, title, streamService, watched);
    }
}

