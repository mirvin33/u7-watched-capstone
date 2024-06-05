package com.nashss.se.watched.services;

import com.nashss.se.watched.dynamodb.ContentDao;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Content;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.dynamodb.models.WatchlistWithContent;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;

import java.util.List;

import javax.inject.Inject;


/**
 * Service class to handle business logic for Watchlists and their Content.
 */
public class WatchlistService {
    private final WatchlistDao watchlistDao;
    private final ContentDao contentDao;

    /**
     * Constructs a new WatchlistService.
     *
     * @param watchlistDao the DAO for accessing watchlist data
     * @param contentDao   the DAO for accessing content data
     */
    @Inject
    public WatchlistService(WatchlistDao watchlistDao, ContentDao contentDao) {
        this.watchlistDao = watchlistDao;
        this.contentDao = contentDao;
    }

    /**
     * Retrieves a watchlist along with its associated content.
     *
     * @param watchlistId the ID of the watchlist to retrieve
     * @return a WatchlistWithContent object containing the watchlist and its content
     */
    public WatchlistWithContent getWatchlistWithContent(String watchlistId) {
        // Fetch the watchlist
        Watchlist watchlist = watchlistDao.getWatchlist(watchlistId);
        if (watchlist == null) {
            throw new WatchlistNotFoundException("Watchlist with ID " + watchlistId + " not found.");
        }

        // Fetch the content for the watchlist
        List<Content> contentList = contentDao.getContentByWatchlistId(watchlistId);

        // Return a combined result
        return new WatchlistWithContent(watchlist, contentList);
    }
}

