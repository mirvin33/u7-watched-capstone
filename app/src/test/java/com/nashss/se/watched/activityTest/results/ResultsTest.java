package com.nashss.se.watched.activityTest.results;

import com.nashss.se.watched.activity.results.*;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.models.WatchlistModel;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultsTest {


    @Test
    public void testCreateWatchlistResultBuilder() {
        String id = "1";
        String title = "My Watchlist";
        String userId = "user123";
        List<String> contentSet = new ArrayList<>();

        WatchlistModel watchlist = new WatchlistModel(id, title, userId, contentSet);
        CreateWatchlistResult result = CreateWatchlistResult.builder()
                .withWatchlist(watchlist)
                .build();
        assertEquals(watchlist, result.getWatchlist());
    }

    @Test
    public void testDeleteWatchlistResultBuilder() {
        String message = "Watchlist deleted successfully.";
        DeleteWatchlistResult result = DeleteWatchlistResult.builder()
                .withDeleteResult(message)
                .build();
        assertEquals(message, result.getDeleteResult());
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
       List<String> contentSet = new ArrayList<>();
        WatchlistModel watchlistModel = new WatchlistModel("id", "title", "userId", contentSet);

        UpdateWatchlistResult result = UpdateWatchlistResult.builder()
                .withWatchlist(watchlistModel)
                .build();

        assertEquals(watchlistModel, result.getWatchlist());
    }
}

