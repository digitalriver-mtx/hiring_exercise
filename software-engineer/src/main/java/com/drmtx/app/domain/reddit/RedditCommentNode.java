package com.drmtx.app.domain.reddit;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Minimal model to access comments from the reddit comments endpoint.
 * Uses duck-typing, ignoring all unspecified attributes.
 */
public class RedditCommentNode {

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("data")
    private RedditCommentData data;

    public RedditCommentNode(String data) {
    }

    public RedditCommentNode() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditCommentData getData() {
        return data;
    }

    public void setData(RedditCommentData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RedditCommentNode{" +
                "kind='" + kind + '\'' +
                ", data=" + data +
                '}';
    }
}
