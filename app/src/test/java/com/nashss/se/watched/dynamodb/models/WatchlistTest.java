package com.nashss.se.watched.dynamodb.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WatchlistTest {
    @Test
    public void testGettersAndSetters() {
        // Arrange
        Watchlist watchlist = new Watchlist();
        String expectedId = "test-id";
        String expectedTitle = "test-title";
        String expectedUserId = "test-user";

        // Act
        watchlist.setId(expectedId);
        watchlist.setTitle(expectedTitle);
        watchlist.setUserId(expectedUserId);

        // Assert
        assertEquals(expectedId, watchlist.getId());
        assertEquals(expectedTitle, watchlist.getTitle());
        assertEquals(expectedUserId, watchlist.getUserId());
    }

    @Test
    public void testDefaultConstructor() {
        // Act
        Watchlist watchlist = new Watchlist();

        // Assert
        assertNotNull(watchlist);
    }
}

