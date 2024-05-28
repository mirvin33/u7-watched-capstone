package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.GetWatchlistRequest;
import com.nashss.se.watched.activity.results.GetWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.models.WatchlistModel;

import javax.inject.Inject;

/**
 * Handles the retrieval of a watchlist.
 */
public class GetWatchlistActivity {
    private final WatchlistDao watchlistDao;

    /**
     * Constructs a GetWatchlistActivity with the specified WatchlistDao.
     *
     * @param watchlistDao the WatchlistDao to use for accessing the watchlist data
     */
    @Inject
    public GetWatchlistActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    /**
     * Handles the GetWatchlistRequest and returns a GetWatchlistResult.
     *
     * @param request the request containing the ID of the watchlist to retrieve
     * @return the result containing the retrieved watchlist
     */
    public GetWatchlistResult handleRequest(GetWatchlistRequest request) {
        Watchlist watchlist = watchlistDao.getWatchlist(request.getId());
        WatchlistModel watchlistModel = WatchlistModel.builder().build();
        return GetWatchlistResult.builder().withWatchlist(watchlistModel).build();
    }
}

