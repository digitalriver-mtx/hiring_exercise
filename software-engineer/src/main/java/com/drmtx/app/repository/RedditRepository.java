package com.drmtx.app.repository;

import com.drmtx.app.domain.reddit.RedditCommentNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Repository to access the reddit comments endpoint.
 */
@Repository
public class RedditRepository {

    /**
     * Find comment bodies from the specified reddit endpoint.
     * @param url reddis comment endpoint
     * @return reddis comments
     */
    public RedditCommentNode[] findCommentByUrl(String url) {
        return new RestTemplate().getForObject(url, RedditCommentNode[].class);
    }
}
