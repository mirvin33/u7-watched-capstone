package com.nashss.se.watched.activityTest.result;

import com.nashss.se.watched.activity.results.GetWatchlistResult;
import com.nashss.se.watched.models.WatchlistModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetWatchlistResultTest {
    @Test
    public void builder_withWatchlist_setsWatchlist() {
        // Arrange
        WatchlistModel expectedWatchlist = WatchlistModel.builder().build();

        // Act
        GetWatchlistResult result = GetWatchlistResult.builder().withWatchlist(expectedWatchlist).build();

        // Assert
        assertNotNull(result);
        assertEquals(expectedWatchlist, result.getWatchlist());
    }

    @Test
    public void getWatchlist_returnsCorrectWatchlist() {
        // Arrange
        WatchlistModel expectedWatchlist = WatchlistModel.builder().build();
        GetWatchlistResult result = GetWatchlistResult.builder().withWatchlist(expectedWatchlist).build();

        // Act
        WatchlistModel actualWatchlist = result.getWatchlist();

        // Assert
        assertEquals(expectedWatchlist, actualWatchlist);
    }
}

