package com.nashss.se.watched.activityTest.results;

import com.nashss.se.watched.activity.results.*;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.models.WatchlistModel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultsTest {
    @Test
    public void testCreateWatchlistResultBuilder() {
        Watchlist watchlist = new Watchlist();
        CreateWatchlistResult result = CreateWatchlistResult.builder()
                .withWatchlist(watchlist)
                .build();
        assertEquals(watchlist, result.getWatchlist());
    }

    @Test
    public void testDeleteWatchlistResultBuilder() {
        String message = "Watchlist deleted successfully.";
        DeleteWatchlistResult result = DeleteWatchlistResult.builder()
                .withMessage(message)
                .build();
        assertEquals(message, result.getMessage());
    }

    @Test
    public void testGetWatchlistContentResultBuilder() {
        Watchlist watchlist = new Watchlist();
        GetWatchlistContentResult result = GetWatchlistContentResult.builder()
                .withWatchlist(watchlist)
                .build();
        assertEquals(watchlist, result.getWatchlist());
    }

    @Test
    public void testGetWatchlistsForUserResultBuilder() {
        List<Watchlist> watchlists = Arrays.asList(new Watchlist(), new Watchlist());
        GetWatchlistsForUserResult result = GetWatchlistsForUserResult.builder()
                .withWatchlists(watchlists)
                .build();
        assertEquals(watchlists, result.getWatchlists());
    }

    @Test
    public void testUpdateWatchlistResultBuilder() {
        WatchlistModel watchlistModel = new WatchlistModel("id", "title", "userId");

        UpdateWatchlistResult result = UpdateWatchlistResult.builder()
                .withWatchlist(watchlistModel)
                .build();

        assertEquals(watchlistModel, result.getWatchlist());
    }
}

