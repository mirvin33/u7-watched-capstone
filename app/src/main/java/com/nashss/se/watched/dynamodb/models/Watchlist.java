package com.nashss.se.watched.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

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
    private List<String> contentSet;

    /**
     * Constructs a Watchlist with an empty content set.
     */
    public Watchlist() {
        this.contentSet = new ArrayList<>();
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
    @DynamoDBAttribute(attributeName = "title")
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
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "userId-watchlistId-index", attributeName = "userId")
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
     * Gets the set of content IDs in the watchlist.
     *
     * @return the list of content IDs
     */
    @DynamoDBAttribute(attributeName = "contentSet")
    public List<String> getContentSet() {
        return contentSet;
    }

    /**
     * Sets the set of content IDs in the watchlist.
     *
     * @param contentSet the set of content IDs to set
     */
    public void setContentSet(List<String> contentSet) {
        this.contentSet = contentSet;
    }

    /**
     * Adds a content ID to the watchlist.
     *
     * @param contentId the content ID to add
     */
    public void addContent(String contentId) {
        this.contentSet.add(contentId);
    }

    /**
     * Removes a content ID from the watchlist.
     *
     * @param contentId the content ID to remove
     */
    public void removeContent(String contentId) {
        this.contentSet.remove(contentId);
    }
}

