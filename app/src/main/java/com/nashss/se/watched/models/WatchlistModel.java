package com.nashss.se.watched.models;

import java.util.List;
import java.util.Objects;


/**
 * POJO for Watchlist.
 */
public class WatchlistModel {
    private final String id;
    private final String title;
    private final String userId;
    private final List<String> contentSet;

    /**
     * Constructs a new WatchlistModel.
     *
     * @param id the unique identifier for the watchlist
     * @param title the title of the watchlist
     * @param userId the identifier of the user (usually an email)
     * @param contentSet list of content data.
     */
    public WatchlistModel(String id, String title, String userId, List<String> contentSet) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.contentSet = contentSet;
    }

    /**
     * @return the unique identifier for the watchlist
     */
    public String getId() {
        return id;
    }

    /**
     * @return the title of the watchlist
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the identifier of the user (usually an email)
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @return contentSet
     */
    public List<String> getContentSet() {
        return contentSet;
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
        WatchlistModel that = (WatchlistModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(contentSet, that.contentSet);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, userId, contentSet);
    }

    /**
     * Creates a builder for WatchlistModel.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for WatchlistModel.
     */
    public static class Builder {
        private String id;
        private String title;
        private String userId;
        private List<String> contentSet;

        /**
         * Sets the id for the WatchlistModel being built.
         *
         * @param id the unique identifier for the watchlist
         * @return the current Builder instance
         */
        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the title for the WatchlistModel being built.
         *
         * @param title the title of the watchlist
         * @return the current Builder instance
         */
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the userId for the WatchlistModel being built.
         *
         * @param userId the identifier of the user (usually an email)
         * @return the current Builder instance
         */
        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Sets the userId for the WatchlistModel being built.
         * @param contentSet List of content.
         * @return contentSet as list.
         */
        public Builder withContentSet(List<String> contentSet) {
            this.contentSet = contentSet;
            return this;
        }

        /**
         * Builds and returns a WatchlistModel instance.
         *
         * @return a new WatchlistModel instance
         */
        public WatchlistModel build() {
            return new WatchlistModel(id, title, userId, contentSet);
        }
    }
}

