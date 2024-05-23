package com.nashss.se.watched.models;

import java.util.Objects;

/**
 * POJO for Watchlist.
 */
public class WatchlistModel {
    private final String id;
    private final String title;
    private final String userId;

    /**
     * Constructs a new WatchlistModel.
     *
     * @param id the unique identifier for the watchlist
     * @param title the title of the watchlist
     * @param userId the identifier of the user (usually an email)
     */
    public WatchlistModel(String id, String title, String userId) {
        this.id = id;
        this.title = title;
        this.userId = userId;
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
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getUserId());
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

        /**
         * Sets the id for the WatchlistModel being built.
         *
         * @param pId the unique identifier for the watchlist
         * @return the current Builder instance
         */
        public Builder withId(String pId) {
            this.id = pId;
            return this;
        }

        /**
         * Sets the title for the WatchlistModel being built.
         *
         * @param pTitle the title of the watchlist
         * @return the current Builder instance
         */
        public Builder withTitle(String pTitle) {
            this.title = pTitle;
            return this;
        }

        /**
         * Sets the userId for the WatchlistModel being built.
         *
         * @param pUserId the identifier of the user (usually an email)
         * @return the current Builder instance
         */
        public Builder withUserId(String pUserId) {
            this.userId = pUserId;
            return this;
        }

        /**
         * Builds and returns a WatchlistModel instance.
         *
         * @return a new WatchlistModel instance
         */
        public WatchlistModel build() {
            return new WatchlistModel(id, title, userId);
        }
    }
}

