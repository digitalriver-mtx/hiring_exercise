package com.drmtx.reddit;

import java.util.Optional;

/**
 * Created by antivo on 8/27/15.
 */
public interface RedditClient {
    Optional<String> getCommentsResponseFor(String redditCommentURL) throws IllegalArgumentException;
}
