package com.nashss.se.watched.activityTest.request;

import com.nashss.se.watched.activity.request.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RequestTest {

    @Test
    public void testAddContentToWatchlistRequest() {
        AddContentToWatchlistRequest request = AddContentToWatchlistRequest.builder()
                .withId("user123")
                .withContentId("content456")
                .withWatched(true)
                .build();
        assertEquals("user123", request.getId());
        assertEquals("content456", request.getContentId());
        assertTrue(request.getWatched());
    }

    @Test
    public void testCreateWatchlistRequest() {
        CreateWatchlistRequest request = CreateWatchlistRequest.builder()
                .withTitle("My Watchlist")
                .withUserId("user123")
                .build();
        assertEquals("My Watchlist", request.getTitle());
        assertEquals("user123", request.getUserId());
    }

    @Test
    public void testDeleteWatchlistRequest() {
        DeleteWatchlistRequest request = DeleteWatchlistRequest.builder()
                .withId("watchlist789")
                .build();
        assertEquals("watchlist789", request.getId());
    }

    @Test
    public void testGetWatchlistContentRequest() {
        GetWatchlistContentRequest request = GetWatchlistContentRequest.builder()
                .withId("watchlist123")
                .build();
        assertEquals("watchlist123", request.getId());
    }


    @Test
    public void testGetWatchlistRequest() {
        GetWatchlistRequest request = GetWatchlistRequest.builder()
                .withId("watchlist456")
                .build();
        assertEquals("watchlist456", request.getId());
    }

    @Test
    public void testGetWatchlistsForUserRequest() {
        GetWatchlistsForUserRequest request = GetWatchlistsForUserRequest.builder()
                .withUserId("user789")
                .build();
        assertEquals("user789", request.getUserId());
    }

    @Test
    public void testUpdateWatchlistRequest() {
        UpdateWatchlistRequest request = UpdateWatchlistRequest.builder()
                .withId("watchlist789")
                .withTitle("New Title")
                .build();
        assertEquals("watchlist789", request.getId());
        assertEquals("New Title", request.getTitle());
    }
}

