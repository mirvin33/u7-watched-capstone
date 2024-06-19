package com.nashss.se.watched.activityTest;

import com.nashss.se.watched.activity.CreateWatchlistActivity;
import com.nashss.se.watched.activity.request.CreateWatchlistRequest;
import com.nashss.se.watched.activity.results.CreateWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.InvalidAttributeValueException;
import com.nashss.se.watched.models.WatchlistModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateWatchlistActivityTest {
    @Mock
    private WatchlistDao watchlistDao;

    @InjectMocks
    private CreateWatchlistActivity createWatchlistActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_ValidName_CreatesWatchlist() {
        // Arrange
        CreateWatchlistRequest request = new CreateWatchlistRequest("Test Watchlist", "userId");
        Watchlist watchlist = new Watchlist();
        when(watchlistDao.saveWatchlist(any())).thenReturn(watchlist);

        // Act
        CreateWatchlistResult result = createWatchlistActivity.handleRequest(request);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getWatchlist());
        assertEquals("Test Watchlist", result.getWatchlist().getTitle());
        assertEquals("userId", result.getWatchlist().getUserId());
        verify(watchlistDao, times(1)).saveWatchlist(any(Watchlist.class));
    }

    @Test
    void handleRequest_InvalidName_ThrowsException() {
        // Arrange
        CreateWatchlistRequest request = new CreateWatchlistRequest("Test'Watchlist", "userId");

        // Act & Assert
        assertThrows(InvalidAttributeValueException.class, () -> createWatchlistActivity.handleRequest(request));
        verify(watchlistDao, never()).saveWatchlist(any());
    }

    @Test
    void handleRequest_ValidNameAndUserId_CreatesWatchlistWithNonNullFields() {
        // Arrange
        String title = "Another Watchlist";
        String userId = "anotherUserId";
        CreateWatchlistRequest request = new CreateWatchlistRequest(title, userId);

        Watchlist watchlist = new Watchlist();
        watchlist.setId(UUID.randomUUID().toString());
        watchlist.setTitle(title);
        watchlist.setUserId(userId);
        watchlist.setContentSet(Collections.emptyList());

        when(watchlistDao.saveWatchlist(any(Watchlist.class))).thenReturn(watchlist);

        // Act
        CreateWatchlistResult result = createWatchlistActivity.handleRequest(request);

        // Assert
        assertNotNull(result);
        WatchlistModel watchlistModel = result.getWatchlist();
        assertNotNull(watchlistModel);
        assertEquals(title, watchlistModel.getTitle());
        assertEquals(userId, watchlistModel.getUserId());
        assertNotNull(watchlistModel.getId());
        assertTrue(watchlistModel.getContentSet().isEmpty());
        verify(watchlistDao, times(1)).saveWatchlist(any(Watchlist.class));
    }
}


