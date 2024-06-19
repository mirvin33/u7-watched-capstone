package com.nashss.se.watched.activityTest.results;

import com.nashss.se.watched.activity.results.GetWatchlistResult;
import com.nashss.se.watched.models.WatchlistModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void builder_withNullWatchlist_setsNullWatchlist() {
        // Act
        GetWatchlistResult result = GetWatchlistResult.builder().withWatchlist(null).build();

        // Assert
        assertNotNull(result);
        assertNull(result.getWatchlist());
    }

    @Test
    public void builder_withoutWatchlistField_setsNullWatchlist() {
        // Act
        GetWatchlistResult result = GetWatchlistResult.builder().build();

        // Assert
        assertNotNull(result);
        assertNull(result.getWatchlist());
    }

    @Test
    public void watchlistModelBuilder_withAllFields_createsCorrectModel() {
        // Arrange
        String id = "2";
        String title = "Another Watchlist";
        String userId = "user456";
        WatchlistModel expectedWatchlist = WatchlistModel.builder()
                .withId(id)
                .withTitle(title)
                .withUserId(userId)
                .build();

        // Act
        WatchlistModel actualWatchlist = WatchlistModel.builder()
                .withId(id)
                .withTitle(title)
                .withUserId(userId)
                .build();

        // Assert
        assertEquals(expectedWatchlist, actualWatchlist);
    }

    @Test
    public void watchlistModelBuilder_withEmptyFields_createsModelWithEmptyFields() {
        // Arrange
        WatchlistModel expectedWatchlist = WatchlistModel.builder().build();

        // Act
        WatchlistModel actualWatchlist = WatchlistModel.builder().build();

        // Assert
        assertEquals(expectedWatchlist, actualWatchlist);
    }
}

