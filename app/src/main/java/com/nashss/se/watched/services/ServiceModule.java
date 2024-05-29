package com.nashss.se.watched.services;

import com.nashss.se.watched.activity.CreateWatchlistActivity;
import com.nashss.se.watched.activity.GetWatchlistActivity;
import com.nashss.se.watched.activity.UpdateWatchlistActivity;
import com.nashss.se.watched.dynamodb.WatchlistDao;
import com.nashss.se.watched.metrics.MetricsPublisher;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Dagger module for providing services such as AWS clients and DAOs.
 */
@Module
public class ServiceModule {

    /**
     * Provides an AmazonDynamoDB client.
     *
     * @return an AmazonDynamoDB client instance
     */
    @Provides
    @Singleton
    public AmazonDynamoDB provideAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.defaultClient();
    }

    /**
     * Provides a DynamoDBMapper using the given AmazonDynamoDB client.
     *
     * @param amazonDynamoDB the AmazonDynamoDB client
     * @return a DynamoDBMapper instance
     */
    @Provides
    @Singleton
    public DynamoDBMapper provideDynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

    /**
     * Provides an AmazonCloudWatch client.
     *
     * @return an AmazonCloudWatch client instance
     */
    @Provides
    @Singleton
    public AmazonCloudWatch provideAmazonCloudWatch() {
        return AmazonCloudWatchClientBuilder.defaultClient();
    }

    /**
     * Provides a MetricsPublisher using the given AmazonCloudWatch client.
     *
     * @param cloudWatch the AmazonCloudWatch client
     * @return a MetricsPublisher instance
     */
    @Provides
    @Singleton
    public MetricsPublisher provideMetricsPublisher(AmazonCloudWatch cloudWatch) {
        return new MetricsPublisher(cloudWatch);
    }

    /**
     * Provides a WatchlistDao using the given DynamoDBMapper and MetricsPublisher.
     *
     * @param dynamoDbMapper the DynamoDBMapper
     * @param metricsPublisher the MetricsPublisher
     * @return a WatchlistDao instance
     */
    @Provides
    @Singleton
    public WatchlistDao provideWatchlistDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        return new WatchlistDao(dynamoDbMapper, metricsPublisher);
    }

    /**
     * Provides a GetWatchlistActivity using the given WatchlistDao.
     *
     * @param watchlistDao the WatchlistDao
     * @return a GetWatchlistActivity instance
     */
    @Provides
    @Singleton
    public GetWatchlistActivity provideGetWatchlistActivity(WatchlistDao watchlistDao) {
        return new GetWatchlistActivity(watchlistDao);
    }

    /**
     *
     * @param watchlistDao the WatchlistDao
     * @return a CreateWatchlistActivity instance
     */
    @Provides
    public CreateWatchlistActivity provideCreateWatchlistActivity(WatchlistDao watchlistDao) {
        return new CreateWatchlistActivity(watchlistDao);
    }

    @Provides
    public UpdateWatchlistActivity provideUpdateWatchlistActivity(WatchlistDao watchlistDao) {
        return new UpdateWatchlistActivity(watchlistDao);
    }

//    @Provides
//    public AddContentToWatchlistActivity provideAddContentToWatchlistActivity(WatchlistDao watchlistDao) {
//        return new AddContentToWatchlistActivity(watchlistDao);
//    }
//
//    @Provides
//    public DeleteWatchlistActivity provideDeleteWatchlistActivity(WatchlistDao watchlistDao) {
//        return new DeleteWatchlistActivity(watchlistDao);
//    }
//
//    @Provides
//    public GetWatchlistContentActivity provideGetWatchlistContentActivity(WatchlistDao watchlistDao) {
//        return new GetWatchlistContentActivity(watchlistDao);
//    }
//
//    @Provides
//    public GetWatchlistsForUserActivity provideGetWatchlistsForUserActivity(WatchlistDao watchlistDao) {
//        return new GetWatchlistsForUserActivity(watchlistDao);
//    }
}


