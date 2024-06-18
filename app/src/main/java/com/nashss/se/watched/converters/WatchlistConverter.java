package com.nashss.se.watched.converters;

import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.models.WatchlistModel;

/**
 * Converts between Watchlist entity and WatchlistModel.
 */
public class WatchlistConverter {

    /**
     * Converts a Watchlist entity to a WatchlistModel.
     *
     * @param watchlist the Watchlist entity to convert
     * @return the converted WatchlistModel
     */
    public static WatchlistModel toWatchlistModel(Watchlist watchlist) {
        return WatchlistModel.builder()
                .withId(watchlist.getId())
                .withTitle(watchlist.getTitle())
                .withUserId(watchlist.getUserId())
                .withContentSet(watchlist.getContentSet())
                .build();
    }

    /**
     * Converts a WatchlistModel to a Watchlist entity.
     *
     * @param model the WatchlistModel to convert
     * @return the converted Watchlist entity
     */
    public static Watchlist toWatchlistEntity(WatchlistModel model) {
        Watchlist watchlist = new Watchlist();
        watchlist.setId(model.getId());
        watchlist.setTitle(model.getTitle());
        watchlist.setUserId(model.getUserId());
        watchlist.setContentSet(model.getContentSet());
        return watchlist;
    }
}

