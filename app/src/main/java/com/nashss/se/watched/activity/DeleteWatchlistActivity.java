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

    /**
     * Constructs a DeleteWatchlistActivity with the given WatchlistDao.
     *
     * @param watchlistDao the WatchlistDao to interact with the database
     */
    @Inject
    public DeleteWatchlistActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    /**
     * Handles the request to delete a watchlist.
     *
     * @param request the request containing the ID of the watchlist to be deleted
     * @return the result of the delete operation
     * @throws WatchlistNotFoundException if the watchlist is not found
     */
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

