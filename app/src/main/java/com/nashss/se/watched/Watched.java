package com.nashss.se.watched;

//import com.nashss.se.watched.dynamodb.ContentDao;
//import com.nashss.se.watched.dynamodb.WatchlistDao;
//import com.nashss.se.watched.dynamodb.models.Content;
//import com.nashss.se.watched.dynamodb.models.Watchlist;
//import com.nashss.se.watched.dynamodb.models.WatchlistWithContent;
//import com.nashss.se.watched.exceptions.WatchlistNotFoundException;
//import com.nashss.se.watched.metrics.MetricsPublisher;
//import com.nashss.se.watched.services.WatchlistService;
//
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//
//import java.util.List;
//
//

public class Watched {
//    public void main(String[] args) {
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
//
//        DynamoDBMapper mapper = new DynamoDBMapper(client);
//
//        MetricsPublisher metricsPublisher = new MetricsPublisher();
//        WatchlistDao watchlistDao = new WatchlistDao(mapper, metricsPublisher);
//        ContentDao contentDao = new ContentDao(mapper, metricsPublisher);
//
//        WatchlistService watchlistService = new WatchlistService(watchlistDao, contentDao);
//
//        try {
//            WatchlistWithContent watchlistWithContent = watchlistService
//            .getWatchlistWithContent("exampleWatchlistId");
//            Watchlist watchlist = watchlistWithContent.getWatchlist();
//            List<Content> contentList = watchlistWithContent.getContentList();
//
//            System.out.println("Watchlist: " + watchlist.getTitle());
//            for (Content content : contentList) {
//                System.out.println("Content: " + content.getTitle() + " - " + content.getStreamService());
//            }
//        } catch (WatchlistNotFoundException e) {
//            System.err.println(e.getMessage());
//        }
//    }
}

