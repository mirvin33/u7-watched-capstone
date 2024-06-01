package com.nashss.se.watched.activityTest;


import com.nashss.se.watched.activity.DeleteWatchlistActivity;
import com.nashss.se.watched.activity.request.DeleteWatchlistRequest;
import com.nashss.se.watched.activity.results.DeleteWatchlistResult;
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

class DeleteWatchlistActivityTest {

    @Mock
    private WatchlistDao watchlistDao;

    @InjectMocks
    private DeleteWatchlistActivity deleteWatchlistActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_ExistingWatchlist_DeletesWatchlist() {
        // Arrange
        DeleteWatchlistRequest request = new DeleteWatchlistRequest("watchlistId");
        Watchlist watchlist = new Watchlist();
        when(watchlistDao.getWatchlist("watchlistId")).thenReturn(watchlist);

        // Act
        DeleteWatchlistResult result = deleteWatchlistActivity.handleRequest(request);

        // Assert
        assertNotNull(result);
        assertEquals("Watchlist successfully deleted", result.getMessage());
        verify(watchlistDao, times(1)).deleteWatchlist("watchlistId");
    }

    @Test
    void handleRequest_NonExistingWatchlist_ThrowsException() {
        // Arrange
        DeleteWatchlistRequest request = new DeleteWatchlistRequest("nonExistingWatchlistId");
        when(watchlistDao.getWatchlist("nonExistingWatchlistId")).thenReturn(null);

        // Act & Assert
        assertThrows(WatchlistNotFoundException.class, () -> deleteWatchlistActivity.handleRequest(request));
        verify(watchlistDao, never()).deleteWatchlist(any());
    }
}

