package com.nashss.se.watched.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    void testWatchlistModelInequalityDifferentTitles() {
        List<String> contentSet = Arrays.asList("content1", "content2", "content3");

        WatchlistModel watchlist1 = WatchlistModel.builder()
                .withId("watchlist1")
                .withTitle("My Watchlist")
                .withUserId("user1")
                .withContentSet(contentSet)
                .build();

        WatchlistModel watchlist2 = WatchlistModel.builder()
                .withId("watchlist1")
                .withTitle("Another Watchlist")
                .withUserId("user1")
                .withContentSet(contentSet)
                .build();

        assertNotEquals(watchlist1, watchlist2);
    }

    @Test
    void testWatchlistModelInequalityDifferentUsers() {
        List<String> contentSet = Arrays.asList("content1", "content2", "content3");

        WatchlistModel watchlist1 = WatchlistModel.builder()
                .withId("watchlist1")
                .withTitle("My Watchlist")
                .withUserId("user1")
                .withContentSet(contentSet)
                .build();

        WatchlistModel watchlist2 = WatchlistModel.builder()
                .withId("watchlist1")
                .withTitle("My Watchlist")
                .withUserId("user2")
                .withContentSet(contentSet)
                .build();

        assertNotEquals(watchlist1, watchlist2);
    }

    @Test
    void testWatchlistModelInequalityDifferentContentSets() {
        List<String> contentSet1 = Arrays.asList("content1", "content2", "content3");
        List<String> contentSet2 = Arrays.asList("content1", "content2");

        WatchlistModel watchlist1 = WatchlistModel.builder()
                .withId("watchlist1")
                .withTitle("My Watchlist")
                .withUserId("user1")
                .withContentSet(contentSet1)
                .build();

        WatchlistModel watchlist2 = WatchlistModel.builder()
                .withId("watchlist1")
                .withTitle("My Watchlist")
                .withUserId("user1")
                .withContentSet(contentSet2)
                .build();

        assertNotEquals(watchlist1, watchlist2);
    }
}


