package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.CreateWatchlistRequest;
import com.nashss.se.watched.activity.results.CreateWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.InvalidAttributeValueException;
import com.nashss.se.watched.models.WatchlistModel;

import javax.inject.Inject;
import java.util.UUID;


/**
 * Activity to create a new watchlist.
 */
public class CreateWatchlistActivity {
    private final WatchlistDao watchlistDao;

    @Inject
    public CreateWatchlistActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    public CreateWatchlistResult handleRequest(CreateWatchlistRequest request) {
        validateWatchlistName(request.getTitle());

        Watchlist watchlist = new Watchlist();
        watchlist.setId(UUID.randomUUID().toString());
        watchlist.setTitle(request.getTitle());
        watchlist.setUserId(request.getUserId());


        watchlistDao.saveWatchlist(watchlist);

        WatchlistModel watchlistModel = WatchlistModel.builder()
                .withId(watchlist.getId())
                .withTitle(watchlist.getTitle())
                .withUserId(watchlist.getUserId())
                .build();

        return CreateWatchlistResult.builder()
                .withWatchlist(watchlist)
                .build();
    }

    private void validateWatchlistName(String name) {
        if (name.contains("\"") || name.contains("'")) {
            throw new InvalidAttributeValueException("Invalid characters in watchlist name");
        }
    }
}

