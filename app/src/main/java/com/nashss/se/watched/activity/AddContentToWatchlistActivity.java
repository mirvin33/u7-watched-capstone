package com.nashss.se.watched.activity;

import com.nashss.se.watched.activity.request.AddContentToWatchlistRequest;
import com.nashss.se.watched.activity.results.AddContentToWatchlistResult;
import com.nashss.se.watched.dynamodb.ContentDao;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Content;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.ContentNotFoundException;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;

import javax.inject.Inject;

/**
 * Activity to add content to a watchlist.
 */
public class AddContentToWatchlistActivity {
    private final WatchlistDao watchlistDao;
    private final ContentDao contentDao;

    /**
     * Constructs an AddContentToWatchlistActivity with the given WatchlistDao.
     *
     * @param watchlistDao the WatchlistDao to interact with the database
     */
    @Inject
    public AddContentToWatchlistActivity(WatchlistDao watchlistDao, ContentDao contentDao) {
        this.watchlistDao = watchlistDao;
        this.contentDao = contentDao;
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

//        Content content = contentDao.getContent(request.getContentId());
//        if (content == null) {
//            throw new ContentNotFoundException("Content not found with ID: " + request.getContentId());
//        }
            watchlist.addContent(request.getContentId());
            watchlistDao.saveWatchlist(watchlist);

            return AddContentToWatchlistResult.builder()
                    .withWatchlist(watchlist)
                    .build();
        }
}

