package com.nashss.se.watched.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.watched.dynamodb.ContentDao;
import com.nashss.se.watched.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContentDaoTest {
    @Mock
    private DynamoDBMapper dynamoDbMapper;

    @Mock
    private MetricsPublisher metricsPublisher;

    @InjectMocks
    private ContentDao contentDao;

    @Captor
    private ArgumentCaptor<DynamoDBScanExpression> scanExpressionCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetContentById() {
        String contentId = "testContentId";
        Content mockContent = new Content();
        mockContent.setContentId(contentId);

        when(dynamoDbMapper.load(Content.class, contentId)).thenReturn(mockContent);

        Content result = contentDao.getContentById(contentId);

        assertNotNull(result);
        assertEquals(contentId, result.getContentId());
        verify(dynamoDbMapper).load(Content.class, contentId);
    }

    @Test
    public void testSaveContent() {
        Content content = new Content();
        content.setContentId("testContentId");

        doNothing().when(dynamoDbMapper).save(content);

        contentDao.saveContent(content);

        verify(dynamoDbMapper).save(content);
    }

    @Test
    public void testDeleteContent() {
        String contentId = "testContentId";
        Content mockContent = new Content();
        mockContent.setContentId(contentId);

        when(dynamoDbMapper.load(Content.class, contentId)).thenReturn(mockContent);
        doNothing().when(dynamoDbMapper).delete(mockContent);

        contentDao.deleteContent(contentId);

        verify(dynamoDbMapper).load(Content.class, contentId);
        verify(dynamoDbMapper).delete(mockContent);
    }

    @Test
    public void testGetContentByWatchlistId() {
        String watchlistId = "watchlistId";
        List<Content> expectedContentList = new ArrayList<>();
        Content content1 = new Content();
        Content content2 = new Content();
        expectedContentList.add(content1);
        expectedContentList.add(content2);

        PaginatedScanList<Content> paginatedScanList = mock(PaginatedScanList.class);
        when(paginatedScanList.iterator()).thenReturn(expectedContentList.iterator());

        when(dynamoDbMapper.scan(eq(Content.class), any(DynamoDBScanExpression.class)))
                .thenReturn(paginatedScanList);

        List<Content> result = contentDao.getContentByWatchlistId(watchlistId);

        assertEquals(expectedContentList, result);
        verify(dynamoDbMapper).scan(eq(Content.class), any(DynamoDBScanExpression.class));
    }
}

