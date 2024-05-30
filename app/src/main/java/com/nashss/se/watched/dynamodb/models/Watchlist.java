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

    /**
     * Constructs a Watchlist with an empty content list.
     */
    public Watchlist() {
        this.contentList = new ArrayList<>();
    }

    /**
     * Gets the ID of the watchlist.
     *
     * @return the ID of the watchlist
     */
    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the watchlist.
     *
     * @param id the ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the title of the watchlist.
     *
     * @return the title of the watchlist
     */
    @DynamoDBAttribute(attributeName = "watchlistTitle")
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the watchlist.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the user ID associated with the watchlist.
     *
     * @return the user ID
     */
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the watchlist.
     *
     * @param userId the user ID to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the list of content IDs in the watchlist.
     *
     * @return the list of content IDs
     */
    @DynamoDBAttribute(attributeName = "contentList")
    public List<String> getContentList() {
        return contentList;
    }

    /**
     * Sets the list of content IDs in the watchlist.
     *
     * @param contentList the list of content IDs to set
     */
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

