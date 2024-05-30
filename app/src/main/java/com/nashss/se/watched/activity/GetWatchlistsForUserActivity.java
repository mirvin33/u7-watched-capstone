package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.GetWatchlistsForUserRequest;
import com.nashss.se.watched.activity.results.GetWatchlistsForUserResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;

import javax.inject.Inject;

/**
 * Activity to retrieve watchlists for a user.
 */
public class GetWatchlistsForUserActivity {
    private final WatchlistDao watchlistDao;

    /**
     * Constructs a GetWatchlistsForUserActivity with the specified WatchlistDao.
     *
     * @param watchlistDao the WatchlistDao to use for accessing the watchlist data
     */
    @Inject
    public GetWatchlistsForUserActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    /**
     * Handles the GetWatchlistsForUserRequest and returns a GetWatchlistsForUserResult.
     *
     * @param request the request containing the user ID whose watchlists are to be retrieved
     * @return the result containing the retrieved watchlists
     */
    public GetWatchlistsForUserResult handleRequest(GetWatchlistsForUserRequest request) {
        return GetWatchlistsForUserResult.builder()
                .withWatchlists(watchlistDao.getWatchlistsForUser(request.getUserId()))
                .build();
    }
}

