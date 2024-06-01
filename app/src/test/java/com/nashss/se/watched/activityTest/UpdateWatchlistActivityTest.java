package com.nashss.se.watched.activityTest;

import com.nashss.se.watched.activity.UpdateWatchlistActivity;
import com.nashss.se.watched.activity.request.UpdateWatchlistRequest;
import com.nashss.se.watched.activity.results.UpdateWatchlistResult;
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


public class UpdateWatchlistActivityTest {
    @Mock
    private WatchlistDao watchlistDao;

    @InjectMocks
    private UpdateWatchlistActivity updateWatchlistActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_UpdatesWatchlist() {
        // Arrange
        String watchlistId = "watchlistId";
        String newTitle = "New Title";
        UpdateWatchlistRequest request = new UpdateWatchlistRequest(watchlistId, newTitle);
        Watchlist watchlist = new Watchlist();
        watchlist.setId(watchlistId);
        when(watchlistDao.getWatchlist(watchlistId)).thenReturn(watchlist);

        // Act
        UpdateWatchlistResult result = updateWatchlistActivity.handleRequest(request);

        // Assert
        assertNotNull(result);
        assertEquals(newTitle, result.getWatchlist().getTitle());
    }

    @Test
    void handleRequest_WatchlistNotFound() {
        // Arrange
        String watchlistId = "nonExistentId";
        UpdateWatchlistRequest request = new UpdateWatchlistRequest(watchlistId, "New Title");
        when(watchlistDao.getWatchlist(watchlistId)).thenReturn(null);

        // Act & Assert
        assertThrows(WatchlistNotFoundException.class, () -> {
            updateWatchlistActivity.handleRequest(request);
        });
    }
}
