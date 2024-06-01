package com.nashss.se.watched.services;

import com.nashss.se.watched.dynamodb.ContentDao;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.dynamodb.models.Content;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.dynamodb.models.WatchlistWithContent;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;
import com.nashss.se.watched.services.WatchlistService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class WatchlistServiceTest {
    @Mock
    private WatchlistDao watchlistDao;

    @Mock
    private ContentDao contentDao;

    @InjectMocks
    private WatchlistService watchlistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetWatchlistWithContent() {
        String watchlistId = "watchlistId1";
        Watchlist watchlist = new Watchlist();
        watchlist.setId(watchlistId);

        Content content1 = new Content();
        content1.setContentId("contentId1");
        content1.setTitle("Title 1");
        content1.setUserId("userId1");
        content1.setStreamService("Netflix");
        content1.setWatched(false);

        Content content2 = new Content();
        content2.setContentId("contentId2");
        content2.setTitle("Title 2");
        content2.setUserId("userId1");
        content2.setStreamService("Amazon Prime");
        content2.setWatched(true);

        List<Content> contentList = Arrays.asList(content1, content2);

        when(watchlistDao.getWatchlist(watchlistId)).thenReturn(watchlist);
        when(contentDao.getContentByWatchlistId(watchlistId)).thenReturn(contentList);

        WatchlistWithContent result = watchlistService.getWatchlistWithContent(watchlistId);

        assertEquals(watchlistId, result.getWatchlist().getId());
        assertEquals(contentList.size(), result.getContentList().size());
        for (int i = 0; i < contentList.size(); i++) {
            assertEquals(contentList.get(i), result.getContentList().get(i));
        }

        verify(watchlistDao, times(1)).getWatchlist(watchlistId);
        verify(contentDao, times(1)).getContentByWatchlistId(watchlistId);
    }

    @Test
    void testGetWatchlistWithContentWatchlistNotFound() {
        String watchlistId = "watchlistId1";

        when(watchlistDao.getWatchlist(watchlistId)).thenReturn(null);

        assertThrows(WatchlistNotFoundException.class,
                () -> watchlistService.getWatchlistWithContent(watchlistId));

        verify(watchlistDao, times(1)).getWatchlist(watchlistId);
        verify(contentDao, never()).getContentByWatchlistId(anyString());
    }
}

