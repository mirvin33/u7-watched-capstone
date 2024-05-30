package com.nashss.se.watched.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a record in the watchlist table.
 */
@DynamoDBTable(tableName = "watchlist")
public class Watchlist {
    private String id;
    private String title;
    private String userId;
    private List<String> contentList;

    public Watchlist() {
        this.contentList = new ArrayList<>();
    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "watchlistTitle")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "contentList")
    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    /**
     * Adds a content ID to the watchlist.
     *
     * @param contentId the content ID to add
     * @param queueNext if true, adds the content to the beginning of the list; otherwise, adds to the end
     */
    public void addContent(String contentId, boolean queueNext) {
        if (queueNext) {
            this.contentList.add(0, contentId);
        } else {
            this.contentList.add(contentId);
        }
    }

    /**
     * Removes a content ID from the watchlist.
     *
     * @param contentId the content ID to remove
     */
    public void removeContent(String contentId) {
        this.contentList.remove(contentId);
    }
}

