package com.nashss.se.watched.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;
import com.nashss.se.watched.metrics.MetricsConstants;
import com.nashss.se.watched.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WatchlistDaoTest {

    private DynamoDBMapper dynamoDbMapper;
    private MetricsPublisher metricsPublisher;
    private WatchlistDao watchlistDao;

    @BeforeEach
    public void setUp() {
        dynamoDbMapper = mock(DynamoDBMapper.class);
        metricsPublisher = mock(MetricsPublisher.class);
        watchlistDao = new WatchlistDao(dynamoDbMapper, metricsPublisher);
    }

    @Test
    public void getWatchlist_whenWatchlistExists_returnsWatchlist() {
        // Arrange
        String id = "test-id";
        Watchlist expectedWatchlist = new Watchlist();
        expectedWatchlist.setId(id);

        when(dynamoDbMapper.load(Watchlist.class, id)).thenReturn(expectedWatchlist);

        // Act
        Watchlist actualWatchlist = watchlistDao.getWatchlist(id);

        // Assert
        assertNotNull(actualWatchlist);
        assertEquals(expectedWatchlist, actualWatchlist);
        verify(metricsPublisher).addCount(MetricsConstants.GETWATCHLIST_WATCHLISTNOTFOUND_COUNT, 0);
    }

    @Test
    public void getWatchlist_whenWatchlistDoesNotExist_throwsException() {
        // Arrange
        String id = "non-existent-id";

        when(dynamoDbMapper.load(Watchlist.class, id)).thenReturn(null);

        // Act & Assert
        WatchlistNotFoundException exception = assertThrows(
                WatchlistNotFoundException.class,
                () -> watchlistDao.getWatchlist(id)
        );
        assertEquals("Could not find watchlist with id " + id, exception.getMessage());
        verify(metricsPublisher).addCount(MetricsConstants.GETWATCHLIST_WATCHLISTNOTFOUND_COUNT, 1);
    }

    @Test
    public void saveWatchlist_savesAndReturnsWatchlist() {
        // Arrange
        Watchlist watchlist = new Watchlist();
        watchlist.setId("test-id");

        // Act
        Watchlist savedWatchlist = watchlistDao.saveWatchlist(watchlist);

        // Assert
        verify(dynamoDbMapper).save(watchlist);
        assertEquals(watchlist, savedWatchlist);
    }

//    @Test
//    public void searchWatchlists_withCriteria_returnsMatchingWatchlists() {
//        // Arrange
//        Watchlist expectedWatchlist = new Watchlist();
//        expectedWatchlist.setId("test-id");
//        expectedWatchlist.setTitle("Test Watchlist");
//
//        PaginatedScanList<Watchlist> paginatedScanList = mock(PaginatedScanList.class);
//        when(paginatedScanList.iterator()).thenReturn(Collections.singletonList(expectedWatchlist).iterator());
//
//        DynamoDBMapper dynamoDBMapper = mock(DynamoDBMapper.class);
//        when(dynamoDBMapper.scan(eq(Watchlist.class), any(DynamoDBScanExpression.class)))
//                .thenReturn(paginatedScanList);
//
//        WatchlistDao watchlistDao = new WatchlistDao(dynamoDBMapper, metricsPublisher);
//
//        // Act
//        List<Watchlist> result = watchlistDao.searchWatchlists(new String[]{"title:Test Watchlist"});
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(expectedWatchlist, result.get(0));
//    }
//
//    @Test
//    public void searchWatchlists_withNoCriteria_returnsAllWatchlists() {
//        // Arrange
//        Watchlist expectedWatchlist = new Watchlist();
//        expectedWatchlist.setId("test-id");
//
//        PaginatedScanList<Watchlist> paginatedScanList = mock(PaginatedScanList.class);
//        when(paginatedScanList.iterator()).thenReturn(Collections.singletonList(expectedWatchlist).iterator());
//
//        DynamoDBMapper dynamoDBMapper = mock(DynamoDBMapper.class);
//        when(dynamoDBMapper.scan(eq(Watchlist.class), any(DynamoDBScanExpression.class)))
//                .thenReturn(paginatedScanList);
//
//        WatchlistDao watchlistDao = new WatchlistDao(dynamoDBMapper, metricsPublisher);
//
//        // Act
//        List<Watchlist> result = watchlistDao.searchWatchlists(new String[0]);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(expectedWatchlist, result.get(0));
//    }
}

