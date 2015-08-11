package com.drmtx.app.repository;

import com.drmtx.app.domain.reddit.RedditCommentNode;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class RedditRepositoryIntegrationTest {

    @Test
    public void validateFindByUrlReturnsResult() {
        RedditRepository redditRepository = new RedditRepository();
        RedditCommentNode[] result = redditRepository.findCommentByUrl("https://www.reddit.com/r/java/comments/32pj67/java_reference_in_gta_v_beautiful/.json");
        assertThat(result.length, greaterThan(0));
    }
}
