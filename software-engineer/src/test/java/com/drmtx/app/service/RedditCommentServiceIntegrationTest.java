package com.drmtx.app.service;

import com.drmtx.app.Application;
import com.drmtx.app.domain.WordFrequency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
public class RedditCommentServiceIntegrationTest {

    @Autowired
    private RedditCommentService redditCommentService;

    @Test
    public void validateRedditCommentWordAnalysis() {
        String requestId = redditCommentService
                .importCommentWordFrequencies("https://www.reddit.com/r/java/comments/32pj67/java_reference_in_gta_v_beautiful/.json");

        List<WordFrequency> wordFrequencies = redditCommentService.findWordFrequencies(requestId);
        assertThat(wordFrequencies, is(not(empty())));
    }
}
