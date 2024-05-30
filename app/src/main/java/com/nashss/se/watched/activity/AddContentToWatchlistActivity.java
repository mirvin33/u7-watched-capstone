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

    @Inject
    public AddContentToWatchlistActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    public AddContentToWatchlistResult handleRequest(AddContentToWatchlistRequest request) {
        Watchlist watchlist = watchlistDao.getWatchlist(request.getId());
        if (watchlist == null) {
            throw new WatchlistNotFoundException("Watchlist not found with ID: " + request.getId());
        }

        // Assuming there's a method to add content to a watchlist
        watchlist.addContent(request.getContentId(), request.getQueueNext());
        watchlistDao.saveWatchlist(watchlist);

        return AddContentToWatchlistResult.builder()
                .withWatchlist(watchlist)
                .build();
    }
}

