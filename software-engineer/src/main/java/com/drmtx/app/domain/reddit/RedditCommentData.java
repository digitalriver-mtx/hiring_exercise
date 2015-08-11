package com.drmtx.app.domain.reddit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Minimal model to access comments from the reddit comments endpoint.
 * Uses duck-typing, ignoring all unspecified attributes.
 */
public class RedditCommentData {

    @JsonProperty("children")
    private RedditCommentNode[] children;

    @JsonProperty("replies")
    private RedditCommentNode replies;

    @JsonProperty("body")
    private String body;

    public RedditCommentNode[] getChildren() {
        return children;
    }

    public void setChildren(RedditCommentNode[] children) {
        this.children = children;
    }

    public RedditCommentNode getReplies() {
        return replies;
    }

    public void setReplies(RedditCommentNode replies) {
        this.replies = replies;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "RedditCommentData{" +
                "children=" + Arrays.toString(children) +
                ", replies=" + replies +
                ", body='" + body + '\'' +
                '}';
    }
}
