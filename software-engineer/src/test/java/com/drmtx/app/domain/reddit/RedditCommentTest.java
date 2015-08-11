package com.drmtx.app.domain.reddit;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class RedditCommentTest {
    private final String SOME_BODY_0 = "some body 0";
    private final String SOME_BODY_1 = "some body 1";
    private final String SOME_BODY_2 = "some body 2";

    @Test
    public void validateCommentBodydNodeWithChildAndReplyAccess() {
        RedditComment redditComment = new RedditComment(buildNodeWithChildAndReply(SOME_BODY_0, SOME_BODY_1, SOME_BODY_2));
        assertThat(redditComment.bodies(), containsInAnyOrder(SOME_BODY_0, SOME_BODY_1, SOME_BODY_2));
    }

    private RedditCommentNode buildNodeWithChildAndReply(String nodeBody, String replyBody, String childBody) {
        RedditCommentNode node = buildNodeWithBody(nodeBody);
        node.getData().setReplies(buildNodeWithBody(replyBody));
        node.getData().setChildren(buildNodeWithBody(childBody));
        return node;
    }

    private RedditCommentNode buildNodeWithBody(String body) {
        RedditCommentNode node = new RedditCommentNode();
        RedditCommentData nodeData = new RedditCommentData();
        nodeData.setBody(body);
        node.setData(nodeData);
        return node;
    }
}
