package com.nashss.se.watched.activityTest;

import com.nashss.se.watched.activity.AddContentToWatchlistActivity;
import com.nashss.se.watched.activity.request.AddContentToWatchlistRequest;
import com.nashss.se.watched.activity.results.AddContentToWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AddContentToWatchlistActivityTest {
    @Mock
    private WatchlistDao watchlistDao;

    private AddContentToWatchlistActivity addContentToWatchlistActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addContentToWatchlistActivity = new AddContentToWatchlistActivity(watchlistDao);
    }

    @Test
    void handleRequest_WatchlistNotFound_ThrowsException() {
        // Arrange
        AddContentToWatchlistRequest request = new AddContentToWatchlistRequest("watchlistId", "contentId", false);
        when(watchlistDao.getWatchlist(anyString())).thenReturn(null);

        // Act & Assert
        assertThrows(WatchlistNotFoundException.class, () -> addContentToWatchlistActivity.handleRequest(request));
    }

//    @Test
//    void handleRequest_WatchlistFound_AddsContent() {
//        // Arrange
//        AddContentToWatchlistRequest request = new AddContentToWatchlistRequest("watchlistId", "contentId", false);
//        Watchlist watchlist = new Watchlist();
//        when(watchlistDao.getWatchlist(anyString())).thenReturn(watchlist);
//
//        // Act
//        AddContentToWatchlistResult result = addContentToWatchlistActivity.handleRequest(request);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(watchlist, result.getWatchlist());
//        assertTrue(watchlist.getContentList().contains("contentId"));
//        verify(watchlistDao, times(1)).saveWatchlist(watchlist);
//    }
}
