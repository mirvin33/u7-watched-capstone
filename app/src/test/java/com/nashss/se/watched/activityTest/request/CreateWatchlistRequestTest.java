package com.nashss.se.watched.activityTest.request;

import com.nashss.se.watched.activity.request.CreateWatchlistRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateWatchlistRequestTest {

    @Test
    public void builder_withValidParameters_buildsRequestObject() {
        String title = "Test Watchlist";
        String userId = "user123";

        CreateWatchlistRequest request = CreateWatchlistRequest.builder()
                .withTitle(title)
                .withUserId(userId)
                .build();

        assertNotNull(request);
        assertEquals(title, request.getTitle());
        assertEquals(userId, request.getUserId());
    }

    @Test
    public void toString_returnsCorrectStringRepresentation() {
        String title = "Test Watchlist";
        String userId = "user123";
        CreateWatchlistRequest request = new CreateWatchlistRequest(title, userId);

        String toStringResult = request.toString();

        assertNotNull(toStringResult);
        assertTrue(toStringResult.contains("title='" + title + "'"));
        assertTrue(toStringResult.contains("userId='" + userId + "'"));
    }
}