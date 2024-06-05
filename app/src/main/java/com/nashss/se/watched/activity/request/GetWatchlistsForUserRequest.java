package com.nashss.se.watched.activity.request;


/**
 * Request class for retrieving watchlists for a user.
 */
public class GetWatchlistsForUserRequest {
    private final String userId;

    /**
     * Constructs a GetWatchlistsForUserRequest with the specified user ID.
     *
     * @param userId the ID of the user
     */
    public GetWatchlistsForUserRequest(String userId) {
        this.userId = userId;
    }

    /**
     * Returns the ID of the user whose watchlists are being retrieved.
     *
     * @return the ID of the user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns a new Builder for creating a GetWatchlistsForUserRequest.
     *
     * @return a new Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for creating a GetWatchlistsForUserRequest.
     */
    public static class Builder {
        private String userId;

        /**
         * Sets the ID of the user whose watchlists are being retrieved.
         *
         * @param userId the ID of the user
         * @return this builder
         */
        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Builds and returns the GetWatchlistsForUserRequest.
         *
         * @return the created GetWatchlistsForUserRequest
         */
        public GetWatchlistsForUserRequest build() {
            return new GetWatchlistsForUserRequest(userId);
        }
    }
}


