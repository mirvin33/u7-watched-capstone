package com.nashss.se.watched.activityTest.request;

import com.nashss.se.watched.activity.request.GetWatchlistRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetWatchlistRequestTest {
    @Test
    public void builder_withId_setsId() {
        // Arrange
        String expectedId = "test-id";

        // Act
        GetWatchlistRequest request = GetWatchlistRequest.builder().withId(expectedId).build();

        // Assert
        assertNotNull(request);
        assertEquals(expectedId, request.getId());
    }

    @Test
    public void getId_returnsCorrectId() {
        // Arrange
        String expectedId = "test-id";
        GetWatchlistRequest request = GetWatchlistRequest.builder().withId(expectedId).build();

        // Act
        String actualId = request.getId();

        // Assert
        assertEquals(expectedId, actualId);
    }
}

