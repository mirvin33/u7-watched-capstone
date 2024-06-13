package com.nashss.se.watched.activityTest;


import com.nashss.se.watched.activity.DeleteWatchlistActivity;
import com.nashss.se.watched.activity.request.DeleteWatchlistRequest;
import com.nashss.se.watched.activity.results.DeleteWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;

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
}
