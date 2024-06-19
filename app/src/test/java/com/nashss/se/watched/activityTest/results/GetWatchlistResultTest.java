package com.nashss.se.watched.activityTest.results;

import com.nashss.se.watched.activity.results.GetWatchlistResult;
import com.nashss.se.watched.models.WatchlistModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetWatchlistResultTest {
    @Test
    public void builder_withWatchlist_setsWatchlist() {
        WatchlistModel expectedWatchlist = WatchlistModel.builder().build();

        GetWatchlistResult result = GetWatchlistResult.builder().withWatchlist(expectedWatchlist).build();

        assertNotNull(result);
        assertEquals(expectedWatchlist, result.getWatchlist());
    }

    @Test
    public void getWatchlist_returnsCorrectWatchlist() {
        WatchlistModel expectedWatchlist = WatchlistModel.builder().build();
        GetWatchlistResult result = GetWatchlistResult.builder().withWatchlist(expectedWatchlist).build();

        WatchlistModel actualWatchlist = result.getWatchlist();

        assertEquals(expectedWatchlist, actualWatchlist);
    }

    @Test
    public void builder_withNullWatchlist_setsNullWatchlist() {
        GetWatchlistResult result = GetWatchlistResult.builder().withWatchlist(null).build();

        assertNotNull(result);
        assertNull(result.getWatchlist());
    }

    @Test
    public void builder_withoutWatchlistField_setsNullWatchlist() {
        GetWatchlistResult result = GetWatchlistResult.builder().build();

        assertNotNull(result);
        assertNull(result.getWatchlist());
    }

    @Test
    public void watchlistModelBuilder_withAllFields_createsCorrectModel() {
        String id = "2";
        String title = "Another Watchlist";
        String userId = "user456";
        WatchlistModel expectedWatchlist = WatchlistModel.builder()
                .withId(id)
                .withTitle(title)
                .withUserId(userId)
                .build();

        WatchlistModel actualWatchlist = WatchlistModel.builder()
                .withId(id)
                .withTitle(title)
                .withUserId(userId)
                .build();

        assertEquals(expectedWatchlist, actualWatchlist);
    }

    @Test
    public void watchlistModelBuilder_withEmptyFields_createsModelWithEmptyFields() {
        WatchlistModel expectedWatchlist = WatchlistModel.builder().build();

        WatchlistModel actualWatchlist = WatchlistModel.builder().build();
        
        assertEquals(expectedWatchlist, actualWatchlist);
    }
}

