package com.nashss.se.watched.activityTest;


import com.nashss.se.watched.activity.DeleteWatchlistActivity;
import com.nashss.se.watched.activity.request.DeleteWatchlistRequest;
import com.nashss.se.watched.activity.results.DeleteWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;

import com.nashss.se.watched.exceptions.WatchlistNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteWatchlistActivityTest {

    @Mock
    private WatchlistDao watchlistDao;

    private DeleteWatchlistActivity deleteWatchlistActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteWatchlistActivity = new DeleteWatchlistActivity(watchlistDao);
    }

    @Test
    void handleRequest_validRequest_deletesWatchlist() {
        DeleteWatchlistRequest request = new DeleteWatchlistRequest.Builder()
                .withId("654")
                .withUserId("useremail@email.com")
                .build();

        String message = "This watchlist is deleted";

        when(watchlistDao.deleteWatchlist((anyString()))).thenReturn(message);
        DeleteWatchlistResult result = deleteWatchlistActivity.handleRequest(request);

        assertEquals(message, result.getDeleteResult());
        verify(watchlistDao, times(1)).deleteWatchlist(any());
    }
    @Test
    void handleRequest_watchlistNotFound_throwsException() {
        DeleteWatchlistRequest request = new DeleteWatchlistRequest.Builder()
                .withId("nonexistentId")
                .withUserId("useremail@email.com")
                .build();

        when(watchlistDao.deleteWatchlist(anyString())).thenThrow(new WatchlistNotFoundException("Watchlist not found"));

        assertThrows(WatchlistNotFoundException.class, () -> deleteWatchlistActivity.handleRequest(request));
        verify(watchlistDao, times(1)).deleteWatchlist(anyString());
    }

    @Test
    void handleRequest_validRequest_logsAndDeletesWatchlist() {
        DeleteWatchlistRequest request = new DeleteWatchlistRequest.Builder()
                .withId("654")
                .withUserId("useremail@email.com")
                .build();

        String message = "This watchlist is deleted";

        when(watchlistDao.deleteWatchlist(anyString())).thenReturn(message);

        DeleteWatchlistResult result = deleteWatchlistActivity.handleRequest(request);

        assertNotNull(result);
        assertEquals(message, result.getDeleteResult());
        verify(watchlistDao, times(1)).deleteWatchlist(anyString());
    }
}

