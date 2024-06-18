package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.GetWatchlistsForUserRequest;
import com.nashss.se.watched.activity.results.GetWatchlistsForUserResult;
import com.nashss.se.watched.converters.WatchlistConverter;

import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.models.WatchlistModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity to retrieve watchlists for a user.
 */
public class GetWatchlistsForUserActivity {
    private Logger log = LogManager.getLogger();
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
     * @param getWatchlistsForUserRequest the request containing the user ID whose watchlists are to be retrieved
     * @return the result containing the retrieved watchlists
     */
    public GetWatchlistsForUserResult handleRequest(final GetWatchlistsForUserRequest getWatchlistsForUserRequest) {
        log.info("Handle Request Started for Get All Watchlists: Request ={}", getWatchlistsForUserRequest);

        List<Watchlist> watchlists = watchlistDao.getWatchlistsForUser(getWatchlistsForUserRequest.getUserId());
        log.info("Retrieved Watchlists for User = {}", watchlists);

        List<WatchlistModel> watchlistModels = new ArrayList<>();
        for (Watchlist watchlist : watchlists) {
            watchlistModels.add(WatchlistConverter.toWatchlistModel(watchlist));
        }

        return GetWatchlistsForUserResult.builder()
                .withWatchlists(watchlistModels)
                .build();
    }
}

