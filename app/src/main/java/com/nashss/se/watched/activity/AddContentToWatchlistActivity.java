package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.AddContentToWatchlistRequest;
import com.nashss.se.watched.activity.results.AddContentToWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;

import javax.inject.Inject;

/**
 * Activity to add content to a watchlist.
 */
public class AddContentToWatchlistActivity {
    private final WatchlistDao watchlistDao;

    /**
     * Constructs an AddContentToWatchlistActivity with the given WatchlistDao.
     *
     * @param watchlistDao the WatchlistDao to interact with the database
     */
    @Inject
    public AddContentToWatchlistActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    /**
     * Handles the request to add content to a watchlist.
     *
     * @param request the request containing the watchlist ID and content details
     * @return the result of adding content to the watchlist
     * @throws WatchlistNotFoundException if the watchlist is not found
     */
    public AddContentToWatchlistResult handleRequest(AddContentToWatchlistRequest request) {
        Watchlist watchlist = watchlistDao.getWatchlist(request.getId());
        if (watchlist == null) {
            throw new WatchlistNotFoundException("Watchlist not found with ID: " + request.getId());
        }

//        watchlist.addContent(request.getId(),request.getContentId(),request.getUserId());
        watchlistDao.saveWatchlist(watchlist);

        return AddContentToWatchlistResult.builder()
                .withWatchlist(watchlist)
                .build();
    }
}

