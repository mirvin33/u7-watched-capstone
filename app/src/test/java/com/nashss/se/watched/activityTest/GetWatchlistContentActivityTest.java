package com.nashss.se.watched.activityTest;

import com.nashss.se.watched.activity.GetWatchlistContentActivity;
import com.nashss.se.watched.activity.request.GetWatchlistContentRequest;
import com.nashss.se.watched.activity.results.GetWatchlistContentResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class GetWatchlistContentActivityTest {
    @Mock
    private WatchlistDao watchlistDao;

    @InjectMocks
    private GetWatchlistContentActivity getWatchlistContentActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_ExistingWatchlist_ReturnsContent() {
        // Arrange
        GetWatchlistContentRequest request = new GetWatchlistContentRequest("watchlistId");
        Watchlist watchlist = new Watchlist();
        when(watchlistDao.getWatchlist("watchlistId")).thenReturn(watchlist);

        // Act
        GetWatchlistContentResult result = getWatchlistContentActivity.handleRequest(request);

        // Assert
        assertNotNull(result);
        assertEquals(watchlist, result.getWatchlist());
    }

    @Test
    void handleRequest_NonExistingWatchlist_ThrowsException() {
        // Arrange
        GetWatchlistContentRequest request = new GetWatchlistContentRequest("nonExistingWatchlistId");
        when(watchlistDao.getWatchlist("nonExistingWatchlistId")).thenReturn(null);

        // Act & Assert
        assertThrows(WatchlistNotFoundException.class, () -> getWatchlistContentActivity.handleRequest(request));
    }
}

