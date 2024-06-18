package com.nashss.se.watched.dynamodb;

import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.nashss.se.watched.dynamodb.models.Watchlist;
import com.nashss.se.watched.exceptions.WatchlistNotFoundException;
import com.nashss.se.watched.metrics.MetricsConstants;
import com.nashss.se.watched.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
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
    private DynamoDBMapper dynamoDbMapper;
    private MetricsPublisher metricsPublisher;

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
     * Returns the Watchlist corresponding to the specified id.
     * @param id the Watchlist ID
     * @return the stored Watchlist, or null if none was found.
     */
    public Watchlist getWatchlist(String id) {
        Watchlist watchlist = this.dynamoDbMapper.load(Watchlist.class, id);

        if (watchlist == null) {
            metricsPublisher.addCount(MetricsConstants.GETWATCHLIST_WATCHLISTNOTFOUND_COUNT, 1);
            throw new WatchlistNotFoundException("Could not find watchlist with id " + id);
        }else {
            metricsPublisher.addCount(MetricsConstants.GETWATCHLIST_WATCHLISTNOTFOUND_COUNT, 0);
        }
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
     * Returns a list of Watchlist objects for a specified user ID.
     *
     * @return a list of Watchlist objects for the specified user
     */
    public List<Watchlist> getWatchlistsForUser(String userId) {
        Watchlist watchlists = this.dynamoDbMapper.load(Watchlist.class, userId);

        if (watchlists == null) {
            metricsPublisher.addCount(MetricsConstants.GETWATCHLIST_WATCHLISTNOTFOUND_COUNT, 1);
            throw new WatchlistNotFoundException("Could not find watchlist with email: " + userId);
        }else {
            metricsPublisher.addCount(MetricsConstants.GETWATCHLIST_WATCHLISTNOTFOUND_COUNT, 0);
        }
        return new ArrayList<Watchlist>();
    }

    /**
     * Deletes a watchlist with the specified ID.
     *
     * @param id the watchlist ID.
     * @return message.
     */
    public String deleteWatchlist(String id) {
        Watchlist watchlist = getWatchlist(id);
        if (watchlist != null) {
            dynamoDbMapper.delete(watchlist);
        } else {
            throw new WatchlistNotFoundException("Could not find watchlist with id " + id);
        }
        return "Activity Deleted";
    }
}

