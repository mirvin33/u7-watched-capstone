package com.nashss.se.watched.dynamodb;

import com.nashss.se.watched.dynamodb.models.Content;
import com.nashss.se.watched.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Accesses data for content using {@link Content} to represent the model in DynamoDB.
 */
@Singleton
public class ContentDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a ContentDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the content table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public ContentDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns the {@link Content} corresponding to the specified ID.
     *
     * @param contentId the Content ID
     * @return the stored Content, or null if none was found.
     */
    public Content getContentById(String contentId) {
        return this.dynamoDbMapper.load(Content.class, contentId);
    }

    /**
     * Saves (creates or updates) the given content.
     *
     * @param content The content to save
     */
    public void saveContent(Content content) {
        this.dynamoDbMapper.save(content);
    }

    /**
     * Deletes the content with the specified ID.
     *
     * @param contentId the content ID
     */
    public void deleteContent(String contentId) {
        Content content = getContentById(contentId);
        if (content != null) {
            this.dynamoDbMapper.delete(content);
        }
    }

    /**
     * Returns a list of Content objects for a specified watchlist ID.
     *
     * @param contentId the watchlist ID
     * @return a list of Content objects for the specified watchlist
     */
    public List<Content> getContentByWatchlistId(String contentId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":watchlistId", new AttributeValue().withS(contentId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contentId = :contentId")
                .withExpressionAttributeValues(valueMap);

        return this.dynamoDbMapper.scan(Content.class, scanExpression);
    }
}

