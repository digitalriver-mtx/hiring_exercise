package com.drmtx.reddit;

import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * Created by antivo on 8/27/15.
 */
public interface RedditResponseExtractor {
    List<String> extractCommentsFromResponse(String response) throws ParseException;
}
