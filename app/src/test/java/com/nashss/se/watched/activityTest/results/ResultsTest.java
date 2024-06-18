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
        WatchlistModel watchlistModel1 = WatchlistModel.builder()
                .withId("1")
                .withTitle("Watchlist 1")
                .withUserId("user1")
                .withContentSet(Arrays.asList("item1", "item2"))
                .build();

        WatchlistModel watchlistModel2 = WatchlistModel.builder()
                .withId("2")
                .withTitle("Watchlist 2")
                .withUserId("user2")
                .withContentSet(Arrays.asList("item3", "item4"))
                .build();

        List<WatchlistModel> watchlists = Arrays.asList(watchlistModel1, watchlistModel2);

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

