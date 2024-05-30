package com.nashss.se.watched.activity;


import com.nashss.se.watched.activity.request.UpdateWatchlistRequest;
import com.nashss.se.watched.activity.results.UpdateWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;

import javax.inject.Inject;

/**
 * Activity to update a watchlist.
 */
public class UpdateWatchlistActivity {
    private final WatchlistDao watchlistDao;

    @Inject
    public UpdateWatchlistActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    public UpdateWatchlistResult handleRequest(UpdateWatchlistRequest request) {
        Watchlist watchlist = watchlistDao.getWatchlist(request.getId());
        if (watchlist == null) {
            throw new WatchlistNotFoundException("Watchlist not found with ID: " + request.getId());
        }

        watchlist.setTitle(request.getTitle());
        watchlistDao.saveWatchlist(watchlist);

        return UpdateWatchlistResult.builder()
                .withWatchlist(watchlist)
                .build();
    }
}

