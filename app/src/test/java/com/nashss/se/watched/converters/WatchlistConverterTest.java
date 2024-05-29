package com.nashss.se.watched.converters;

import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.models.WatchlistModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WatchlistConverterTest {

    @Test
    public void toWatchlistModel_withValidWatchlist_returnsWatchlistModel() {
        // Arrange
        Watchlist watchlist = new Watchlist();
        watchlist.setId("test-id");
        watchlist.setTitle("test-title");
        watchlist.setUserId("test-user");

        // Act
        WatchlistModel model = WatchlistConverter.toWatchlistModel(watchlist);

        // Assert
        assertNotNull(model);
        assertEquals(watchlist.getId(), model.getId());
        assertEquals(watchlist.getTitle(), model.getTitle());
        assertEquals(watchlist.getUserId(), model.getUserId());
    }

    @Test
    public void toWatchlistEntity_withValidWatchlistModel_returnsWatchlist() {
        // Arrange
        WatchlistModel model = WatchlistModel.builder()
                .withId("test-id")
                .withTitle("test-title")
                .withUserId("test-user")
                .build();

        // Act
        Watchlist watchlist = WatchlistConverter.toWatchlistEntity(model);

        // Assert
        assertNotNull(watchlist);
        assertEquals(model.getId(), watchlist.getId());
        assertEquals(model.getTitle(), watchlist.getTitle());
        assertEquals(model.getUserId(), watchlist.getUserId());
    }
}

