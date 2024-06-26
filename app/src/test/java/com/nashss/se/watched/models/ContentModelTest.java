package com.nashss.se.watched.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ContentModelTest {
    @Test
    void testContentModelEquality() {
        ContentModel content1 = ContentModel.builder()
                .withContentId("1")
                .withTitle("Title")
                .withStreamService("Netflix")
                .withWatched(true)
                .build();

        ContentModel content2 = ContentModel.builder()
                .withContentId("1")
                .withTitle("Title")
                .withStreamService("Netflix")
                .withWatched(true)
                .build();

        assertEquals(content1, content2);
    }

    @Test
    void testContentModelInequality() {
        ContentModel content1 = ContentModel.builder()
                .withContentId("1")
                .withTitle("Title")
                .withStreamService("Netflix")
                .withWatched(true)
                .build();

        ContentModel content2 = ContentModel.builder()
                .withContentId("2")
                .withTitle("Title2")
                .withStreamService("Amazon Prime")
                .withWatched(false)
                .build();

        assertNotEquals(content1, content2);
    }

    @Test
    void testContentModelInequalityWithNullContentId() {
        ContentModel content1 = ContentModel.builder()
                .withTitle("Title")
                .withStreamService("Netflix")
                .withWatched(true)
                .build();

        ContentModel contentModel = new ContentModel(null, "Title", "Netflix",
                true);

        ContentModel content2 = ContentModel.builder()
                .withTitle("Title")
                .withStreamService("Netflix")
                .withWatched(true)
                .build();

        assertNotEquals(content1, content2);
    }

    @Test
    void testContentModelInequalityWithDifferentWatchedStatus() {
        ContentModel content1 = ContentModel.builder()
                .withContentId("1")
                .withTitle("Title")
                .withStreamService("Netflix")
                .withWatched(true)
                .build();

        ContentModel content2 = ContentModel.builder()
                .withContentId("1")
                .withTitle("Title")
                .withStreamService("Netflix")
                .withWatched(false)
                .build();

        assertNotEquals(content1, content2);
    }

    @Test
    void testContentModelEqualityWithSameObject() {
        ContentModel content1 = ContentModel.builder()
                .withContentId("1")
                .withTitle("Title")
                .withStreamService("Netflix")
                .withWatched(true)
                .build();

        assertEquals(content1, content1);
    }
}

