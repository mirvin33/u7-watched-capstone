package com.nashss.se.watched.activityTest;

import com.nashss.se.watched.activity.GetWatchlistActivity;
import com.nashss.se.watched.activity.request.GetWatchlistRequest;
import com.nashss.se.watched.activity.results.GetWatchlistResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.models.WatchlistModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class GetWatchlistActivityTest {

    @Mock
    private WatchlistDao watchlistDao;

    @InjectMocks
    private GetWatchlistActivity getWatchlistActivity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void handleRequest_withValidRequest_returnsWatchlist() {
        String watchlistId = "test-id";
        GetWatchlistRequest request = GetWatchlistRequest.builder().withId(watchlistId).build();

        Watchlist watchlist = new Watchlist();
        when(watchlistDao.getWatchlist(anyString())).thenReturn(watchlist);

        WatchlistModel watchlistModel = WatchlistModel.builder().build();

        GetWatchlistResult result = getWatchlistActivity.handleRequest(request);

        assertNotNull(result);
        assertNotNull(result.getWatchlist());
    }
}

