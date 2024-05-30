package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.DeleteWatchlistRequest;
import com.nashss.se.watched.activity.results.DeleteWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;

import javax.inject.Inject;

/**
 * Activity to delete a watchlist.
 */
public class DeleteWatchlistActivity {
    private final WatchlistDao watchlistDao;

    @Inject
    public DeleteWatchlistActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    public DeleteWatchlistResult handleRequest(DeleteWatchlistRequest request) {
        Watchlist watchlist = watchlistDao.getWatchlist(request.getId());
        if (watchlist == null) {
            throw new WatchlistNotFoundException("Watchlist not found with ID: " + request.getId());
        }

        watchlistDao.deleteWatchlist(request.getId());

        return DeleteWatchlistResult.builder()
                .withMessage("Watchlist successfully deleted")
                .build();
    }
}

