package com.drmtx.app.domain.reddit;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void setChildren(RedditCommentNode... children) {
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

    /**
     * Get comment bodies of all nodes, child and reply nodes .
     *
     * @return all comment bodies.
     */
    public List<String> bodies() {
        List<String> bodies = new ArrayList<>();
        addBody(bodies);
        addBodyOfChildren(bodies);
        addBodyOfReplies(bodies);
        return bodies;
    }

    private void addBodyOfReplies(List<String> bodies) {
        if (replies != null) {
            bodies.addAll(replies.bodies());
        }
    }

    private void addBodyOfChildren(List<String> bodies) {
        if (children != null) {
            for (RedditCommentNode child : children) {
                bodies.addAll(child.bodies());
            }
        }
    }

    private void addBody(List<String> bodies) {
        if (StringUtils.isNoneBlank(body)) {
            bodies.add(body);
        }
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
