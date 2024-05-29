package com.nashss.se.watched.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ContentModelTest {
    @Test
    void testContentModelEquality() {
        ContentModel content1 = ContentModel.builder()
                .withContentId("1")
                .withWatchlistId("watchlist1")
                .withTitle("Title")
                .withUserId("user1")
                .withStreamService("Netflix")
                .withWatched(true)
                .build();

        ContentModel content2 = ContentModel.builder()
                .withContentId("1")
                .withWatchlistId("watchlist1")
                .withTitle("Title")
                .withUserId("user1")
                .withStreamService("Netflix")
                .withWatched(true)
                .build();

        assertEquals(content1, content2);
    }

    @Test
    void testContentModelInequality() {
        ContentModel content1 = ContentModel.builder()
                .withContentId("1")
                .withWatchlistId("watchlist1")
                .withTitle("Title")
                .withUserId("user1")
                .withStreamService("Netflix")
                .withWatched(true)
                .build();

        ContentModel content2 = ContentModel.builder()
                .withContentId("2")
                .withWatchlistId("watchlist2")
                .withTitle("Title2")
                .withUserId("user2")
                .withStreamService("Amazon Prime")
                .withWatched(false)
                .build();

        assertNotEquals(content1, content2);
    }
}

