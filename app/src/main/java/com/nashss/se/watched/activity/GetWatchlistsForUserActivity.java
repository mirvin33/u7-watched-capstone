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

    @Inject
    public GetWatchlistsForUserActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    public GetWatchlistsForUserResult handleRequest(GetWatchlistsForUserRequest request) {
        return GetWatchlistsForUserResult.builder()
                .withWatchlists(watchlistDao.getWatchlistsForUser(request.getUserId()))
                .build();
    }
}

