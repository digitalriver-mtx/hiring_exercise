package com.drmtx.app.repository;

import com.drmtx.app.domain.reddit.RedditComment;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class RedditRepositoryIntegrationTest {

    @Test
    public void validateFindByUrlReturnsResult() {
        RedditRepository redditRepository = new RedditRepository();
        RedditComment redditComment = redditRepository.findCommentByUrl("https://www.reddit.com/r/java/comments/32pj67/java_reference_in_gta_v_beautiful/.json");
        assertThat(redditComment.bodies().size(), greaterThan(0));
    }
}
