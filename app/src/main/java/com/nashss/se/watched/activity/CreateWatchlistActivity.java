package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.CreateWatchlistRequest;
import com.nashss.se.watched.activity.results.CreateWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.InvalidAttributeValueException;
import com.nashss.se.watched.models.WatchlistModel;

import java.util.Collections;
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
        System.out.println("Received request to create watchlist: " + request);
        validateWatchlistName(request.getTitle());

        Watchlist watchlist = new Watchlist();
        watchlist.setId(UUID.randomUUID().toString());
        watchlist.setTitle(request.getTitle());
        watchlist.setUserId(request.getUserId());
        watchlist.setContentSet(Collections.emptyList());

        System.out.println("Saving watchlist: " + watchlist);
        watchlistDao.saveWatchlist(watchlist);

        WatchlistModel watchlistModel = WatchlistModel.builder()
                .withId(watchlist.getId())
                .withTitle(watchlist.getTitle())
                .withUserId(watchlist.getUserId())
                .withContentSet(watchlist.getContentSet())
                .build();

        System.out.println("Created watchlist result: " + watchlistModel);
        return CreateWatchlistResult.builder()
                .withWatchlist(watchlistModel)
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

