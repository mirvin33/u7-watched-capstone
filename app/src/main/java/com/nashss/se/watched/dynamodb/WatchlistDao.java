package com.nashss.se.watched.dynamodb;

import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;
import com.nashss.se.watched.metrics.MetricsConstants;
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
 * Accesses data for a watchlist using {@link Watchlist} to represent the model in DynamoDB.
 */
@Singleton
public class WatchlistDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a WatchlistDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the watchlist table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public WatchlistDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns the {@link Watchlist} corresponding to the specified id.
     *
     * @param id the Watchlist ID
     * @return the stored Watchlist, or null if none was found.
     */
    public Watchlist getWatchlist(String id) {
        Watchlist watchlist = this.dynamoDbMapper.load(Watchlist.class, id);

        if (watchlist == null) {
            metricsPublisher.addCount(MetricsConstants.GETWATCHLIST_WATCHLISTNOTFOUND_COUNT, 1);
            throw new WatchlistNotFoundException("Could not find watchlist with id " + id);
        }
        metricsPublisher.addCount(MetricsConstants.GETWATCHLIST_WATCHLISTNOTFOUND_COUNT, 0);
        return watchlist;
    }

    /**
     * Saves (creates or updates) the given watchlist.
     *
     * @param watchlist The watchlist to save
     * @return The Watchlist object that was saved
     */
    public Watchlist saveWatchlist(Watchlist watchlist) {
        System.out.println("Saving watchlist to DynamoDB: " + watchlist);
        this.dynamoDbMapper.save(watchlist);
        System.out.println("Saved watchlist to DynamoDB: " + watchlist);
        return watchlist;
    }

    /**
     * Perform a search (via a "scan") of the watchlist table for watchlists matching the given criteria.
     * The "watchlistID" attributes are searched.
     * The criteria are an array of Strings. Each element of the array is search individually.
     * ALL elements of the criteria array must appear in the watchlistName or the tags (or both).
     * Searches are CASE SENSITIVE.
     *
     * @param criteria an array of String containing search criteria.
     * @return a List of watchlist objects that match the search criteria.
     */
    public List<Watchlist> searchWatchlists(String[] criteria) {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();

        if (criteria.length > 0) {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            String valueMapNamePrefix = ":c";

            StringBuilder nameFilterExpression = new StringBuilder();

            for (int i = 0; i < criteria.length; i++) {
                valueMap.put(valueMapNamePrefix + i,
                        new AttributeValue().withS(criteria[i]));
                nameFilterExpression.append(
                        filterExpressionPart("watchlistName", valueMapNamePrefix, i));
            }

            dynamoDBScanExpression.setExpressionAttributeValues(valueMap);
            dynamoDBScanExpression.setFilterExpression(
                    "(" + nameFilterExpression + ")");
        }

        return this.dynamoDbMapper.scan(Watchlist.class, dynamoDBScanExpression);
    }

    private StringBuilder filterExpressionPart(String target, String valueMapNamePrefix, int position) {
        String possiblyAnd = position == 0 ? "" : "and ";
        return new StringBuilder()
                .append(possiblyAnd)
                .append("contains(")
                .append(target)
                .append(", ")
                .append(valueMapNamePrefix).append(position)
                .append(") ");
    }

    /**
     * Returns a list of {@link Watchlist} objects for a specified user ID.
     *
     * @param userId the user ID
     * @return a list of Watchlist objects for the specified user
     */
    public List<Watchlist> getWatchlistsForUser(String userId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":userId", new AttributeValue().withS(userId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("userId = :userId")
                .withExpressionAttributeValues(valueMap);

        return dynamoDbMapper.scan(Watchlist.class, scanExpression);
    }

    /**
     * Deletes a watchlist with the specified ID.
     *
     * @param id the watchlist ID
     */
    public void deleteWatchlist(String id) {
        Watchlist watchlist = getWatchlist(id);
        if (watchlist != null) {
            dynamoDbMapper.delete(watchlist);
        } else {
            throw new WatchlistNotFoundException("Could not find watchlist with id " + id);
        }
    }

//    /**
//     * Adds content to a watchlist with the specified ID.
//     *
//     * @param watchlistId the watchlist ID
//     * @param contentId   the content ID to add
//     * @param queueNext   whether to queue the content next
//     * @return the updated Watchlist object
//     */
//    public Watchlist addContent(String watchlistId, String contentId, boolean queueNext) {
//        Watchlist watchlist = getWatchlist(watchlistId);
//        if (watchlist == null) {
//            throw new WatchlistNotFoundException("Could not find watchlist with id " + watchlistId);
//        }
//
//
////        watchlist.addContent(contentId, queueNext);
//        saveWatchlist(watchlist);
//
//        return watchlist;
//    }
}

