package com.nashss.se.watched.activityTest;

import com.nashss.se.watched.activity.GetWatchlistsForUserActivity;
import com.nashss.se.watched.activity.request.GetWatchlistsForUserRequest;
import com.nashss.se.watched.activity.results.GetWatchlistsForUserResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class GetWatchlistsForUserActivityTest {
    @Mock
    private WatchlistDao watchlistDao;

    @InjectMocks
    private GetWatchlistsForUserActivity getWatchlistsForUserActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_ReturnsWatchlists() {
        // Arrange
        String userId = "userId";
        GetWatchlistsForUserRequest request = new GetWatchlistsForUserRequest(userId);
        List<Watchlist> watchlists = new ArrayList<>();
        when(watchlistDao.getWatchlistsForUser(userId)).thenReturn(watchlists);

        // Act
        GetWatchlistsForUserResult result = getWatchlistsForUserActivity.handleRequest(request);

        // Assert
        assertNotNull(result);
        assertEquals(watchlists, result.getWatchlists());
    }
}
