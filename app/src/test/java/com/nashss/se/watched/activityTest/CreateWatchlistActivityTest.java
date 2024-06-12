package com.nashss.se.watched.activityTest;

import com.nashss.se.watched.activity.CreateWatchlistActivity;
import com.nashss.se.watched.activity.request.CreateWatchlistRequest;
import com.nashss.se.watched.activity.results.CreateWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

//    @Test
//    void testUUIDGeneration() {
//        WatchlistDao watchlistDao = Mockito.mock(WatchlistDao.class);
//
//        CreateWatchlistActivity createWatchlistActivity = new CreateWatchlistActivity(watchlistDao);
//
//        String title = "Test Watchlist";
//        String userId = "testUserId";
//
//        CreateWatchlistRequest request = new CreateWatchlistRequest(title, userId);
//        request.setTitle("Test Watchlist");
//        request.setUserId("testUserId");
//
//        CreateWatchlistResult result = createWatchlistActivity.handleRequest(request);
//
//        String generatedId = result.getWatchlist().getId();
//        UUID uuid;
//        try {
//            uuid = UUID.fromString(generatedId);
//        } catch (IllegalArgumentException e) {
//            uuid = null;
//        }
//        assertEquals(uuid != null, true, "Generated ID is not a valid UUID");
//    }
}

