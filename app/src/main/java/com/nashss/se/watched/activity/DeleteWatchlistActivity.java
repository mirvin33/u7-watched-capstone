package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.DeleteWatchlistRequest;
import com.nashss.se.watched.activity.results.DeleteWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


/**
 * Activity to delete a watchlist.
 */
public class DeleteWatchlistActivity {
    private final WatchlistDao watchlistDao;
    private final Logger log = LogManager.getLogger();

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
     * @return result of the delete operation
     * @throws WatchlistNotFoundException if the watchlist is not found
     */
    public DeleteWatchlistResult handleRequest(final DeleteWatchlistRequest request) {
        log.info("Request To Delete Activity {}", request);
        String id = request.getId();
        String result = watchlistDao.deleteWatchlist(id);

        return new DeleteWatchlistResult.Builder()
                .withDeleteResult(result)
                .build();
    }
}

