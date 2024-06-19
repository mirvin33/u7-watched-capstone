package com.nashss.se.watched.activityTest;

import com.nashss.se.watched.activity.GetWatchlistsForUserActivity;
import com.nashss.se.watched.activity.request.GetWatchlistsForUserRequest;
import com.nashss.se.watched.activity.results.GetWatchlistsForUserResult;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.models.WatchlistModel;
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
        String userId = "userId";

        GetWatchlistsForUserRequest request = GetWatchlistsForUserRequest.builder()
                .withUserId(userId)
                .build();

        List<Watchlist> watchlists = new ArrayList<>();
        Watchlist watchlist = new Watchlist();
        watchlist.setId("1");
        watchlist.setTitle("My Watchlist");
        watchlist.setUserId(userId);
        watchlists.add(watchlist);

        when(watchlistDao.getWatchlistsForUser(userId)).thenReturn(watchlists);

        GetWatchlistsForUserResult result = getWatchlistsForUserActivity.handleRequest(request);

        assertNotNull(result);
        List<WatchlistModel> watchlistModels = result.getWatchlists();
        assertNotNull(watchlistModels);
        assertEquals(1, watchlistModels.size());
        assertEquals("1", watchlistModels.get(0).getId());
        assertEquals("My Watchlist", watchlistModels.get(0).getTitle());
        assertEquals(userId, watchlistModels.get(0).getUserId());
    }

    @Test
    void handleRequest_NoWatchlists_ReturnsEmptyList() {
        String userId = "userId";

        GetWatchlistsForUserRequest request = GetWatchlistsForUserRequest.builder()
                .withUserId(userId)
                .build();

        List<Watchlist> watchlists = new ArrayList<>();

        when(watchlistDao.getWatchlistsForUser(userId)).thenReturn(watchlists);

        GetWatchlistsForUserResult result = getWatchlistsForUserActivity.handleRequest(request);

        assertNotNull(result);
        List<WatchlistModel> watchlistModels = result.getWatchlists();
        assertNotNull(watchlistModels);
        assertEquals(0, watchlistModels.size());
    }

    @Test
    void handleRequest_MultipleWatchlists_ReturnsCorrectWatchlists() {
        String userId = "userId";

        GetWatchlistsForUserRequest request = GetWatchlistsForUserRequest.builder()
                .withUserId(userId)
                .build();

        List<Watchlist> watchlists = new ArrayList<>();
        Watchlist watchlist1 = new Watchlist();
        watchlist1.setId("1");
        watchlist1.setTitle("Watchlist One");
        watchlist1.setUserId(userId);

        Watchlist watchlist2 = new Watchlist();
        watchlist2.setId("2");
        watchlist2.setTitle("Watchlist Two");
        watchlist2.setUserId(userId);

        watchlists.add(watchlist1);
        watchlists.add(watchlist2);

        when(watchlistDao.getWatchlistsForUser(userId)).thenReturn(watchlists);

        GetWatchlistsForUserResult result = getWatchlistsForUserActivity.handleRequest(request);

        assertNotNull(result);
        List<WatchlistModel> watchlistModels = result.getWatchlists();
        assertNotNull(watchlistModels);
        assertEquals(2, watchlistModels.size());

        WatchlistModel model1 = watchlistModels.get(0);
        assertEquals("1", model1.getId());
        assertEquals("Watchlist One", model1.getTitle());
        assertEquals(userId, model1.getUserId());

        WatchlistModel model2 = watchlistModels.get(1);
        assertEquals("2", model2.getId());
        assertEquals("Watchlist Two", model2.getTitle());
        assertEquals(userId, model2.getUserId());
    }
}
