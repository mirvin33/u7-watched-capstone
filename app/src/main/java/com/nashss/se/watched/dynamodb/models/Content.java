package com.nashss.se.watched.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;


@DynamoDBTable(tableName = "content")
public class Content {

    private String contentId;
    private String title;
    private String streamService;
    private Boolean watched;

    @DynamoDBHashKey(attributeName = "contentId")
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @DynamoDBRangeKey(attributeName = "track_number")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
