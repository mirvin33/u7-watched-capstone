package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.CreateWatchlistRequest;
import com.nashss.se.watched.activity.results.CreateWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.InvalidAttributeValueException;
import com.nashss.se.watched.models.WatchlistModel;

import java.util.UUID;
import javax.inject.Inject;




/**
 * Activity to create a new watchlist.
 */
public class CreateWatchlistActivity {
    private final WatchlistDao watchlistDao;

    /**
     * Constructs a CreateWatchlistActivity with the given WatchlistDao.
     *
     * @param watchlistDao the WatchlistDao to interact with the database
     */
    @Inject
    public CreateWatchlistActivity(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    /**
     * Handles the request to create a new watchlist.
     *
     * @param request the request containing the details for the new watchlist
     * @return the result of creating the watchlist
     * @throws InvalidAttributeValueException if the watchlist name contains invalid characters
     */
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

    /**
     * Validates the name of the watchlist.
     *
     * @param name the name of the watchlist
     * @throws InvalidAttributeValueException if the name contains invalid characters
     */
    private void validateWatchlistName(String name) {
        if (name.contains("\"") || name.contains("'")) {
            throw new InvalidAttributeValueException("Invalid characters in watchlist name");
        }
    }
}

