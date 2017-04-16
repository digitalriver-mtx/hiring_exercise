package com.drmtx.app.repository;

import com.drmtx.app.domain.reddit.RedditComment;
import com.drmtx.app.domain.reddit.RedditCommentNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

/**
 * Repository to access the reddit comments endpoint.
 * <p>
 * https://github.com/reddit/reddit/wiki/API#rules
 */
@Repository
public class RedditRepository {

    /**
     * Find comment bodies from the specified reddit endpoint.
     *
     * @param url reddis comment endpoint
     * @return reddis comments
     */
    public RedditComment findCommentByUrl(String url) {
        // add user agent to allow higher api troughput
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("User-Agent", "android:com.drmtx:v1.0 (by bw)");

        // query api
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(new LinkedMultiValueMap<>(), requestHeaders);
        ResponseEntity<RedditCommentNode[]> response = new RestTemplate().exchange(url, GET, requestEntity, RedditCommentNode[].class);

        return new RedditComment(response.getBody());
    }
}
