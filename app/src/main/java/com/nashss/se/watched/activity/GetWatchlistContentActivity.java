package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.GetWatchlistContentRequest;
import com.nashss.se.watched.activity.results.GetWatchlistContentResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;

import javax.inject.Inject;

/**
 * Activity to retrieve watchlist content.
 */
public class GetWatchlistContentActivity {
    private final WatchlistDao watchlistDao;

    @Inject
    public GetWatchlistContentActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    public GetWatchlistContentResult handleRequest(GetWatchlistContentRequest request) {
        Watchlist watchlist = watchlistDao.getWatchlist(request.getId());
        if (watchlist == null) {
            throw new WatchlistNotFoundException("Watchlist not found with ID: " + request.getId());
        }

        return GetWatchlistContentResult.builder()
                .withWatchlist(watchlist)
                .build();
    }
}

