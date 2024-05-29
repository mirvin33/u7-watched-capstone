package com.nashss.se.watched.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WatchlistModelTest {
    @Test
    void testWatchlistModelEquality() {
        WatchlistModel watchlist1 = WatchlistModel.builder()
                .withId("watchlist1")
                .withTitle("My Watchlist")
                .withUserId("user1")
                .build();

        WatchlistModel watchlist2 = WatchlistModel.builder()
                .withId("watchlist1")
                .withTitle("My Watchlist")
                .withUserId("user1")
                .build();

        assertEquals(watchlist1, watchlist2);
    }

    @Test
    void testWatchlistModelInequality() {
        WatchlistModel watchlist1 = WatchlistModel.builder()
                .withId("watchlist1")
                .withTitle("My Watchlist")
                .withUserId("user1")
                .build();

        WatchlistModel watchlist2 = WatchlistModel.builder()
                .withId("watchlist2")
                .withTitle("Another Watchlist")
                .withUserId("user2")
                .build();

        assertNotEquals(watchlist1, watchlist2);
    }
}

