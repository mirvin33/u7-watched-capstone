package com.nashss.se.watched.dynamodb.models;

import java.util.List;

/**
 * Helper class to encapsulate a watchlist along with its associated content.
 */
public class WatchlistWithContent {
    private Watchlist watchlist;
    private List<Content> contentList;

    /**
     * Constructs a new WatchlistWithContent.
     *
     * @param watchlist  the watchlist
     * @param contentList the list of content associated with the watchlist
     */
    public WatchlistWithContent(Watchlist watchlist, List<Content> contentList) {
        this.watchlist = watchlist;
        this.contentList = contentList;
    }

    /**
     * Returns the watchlist.
     *
     * @return the watchlist
     */
    public Watchlist getWatchlist() {
        return watchlist;
    }

    /**
     * Sets the watchlist.
     *
     * @param watchlist the watchlist to set
     */
    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    /**
     * Returns the list of content.
     *
     * @return the list of content
     */
    public List<Content> getContentList() {
        return contentList;
    }

    /**
     * Sets the list of content.
     *
     * @param contentList the list of content to set
     */
    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }
}

