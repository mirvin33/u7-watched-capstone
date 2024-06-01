package com.nashss.se.watched.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "Content")
public class Content {

    private String contentId;
    private String watchlistId;
    private String title;
    private String userId;
    private String streamService;
    private Boolean watched;

    @DynamoDBHashKey(attributeName = "contentId")
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "userId-watchlistId-index", attributeName = "watchlistId")
    public String getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(String watchlistId) {
        this.watchlistId = watchlistId;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "userId-watchlistId-index", attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "streamService")
    public String getStreamService() {
        return streamService;
    }

    public void setStreamService(String streamService) {
        this.streamService = streamService;
    }

    @DynamoDBAttribute(attributeName = "watched")
    public Boolean getWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }
}
