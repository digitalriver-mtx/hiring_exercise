package com.drmtx.app.domain.reddit;

import java.util.ArrayList;
import java.util.List;

/**
 * Model root to access all comments from the reddit comments endpoint.
 */
public class RedditComment {
    private RedditCommentNode[] commentNodes;

    public RedditComment(RedditCommentNode... commentNodes) {
        this.commentNodes = commentNodes;
    }

    /**
     * Get comment bodies of all nodes, child and reply nodes.
     *
     * @return all comment bodies.
     */
    public List<String> bodies() {
        List<String> bodies = new ArrayList<>();
        for (RedditCommentNode commentNode : commentNodes) {
            bodies.addAll(commentNode.bodies());
        }
        return bodies;
    }
}
