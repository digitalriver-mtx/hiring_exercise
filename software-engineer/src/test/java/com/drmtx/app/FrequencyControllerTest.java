package com.drmtx.app;

import com.drmtx.app.model.RedditUrl;
import com.drmtx.app.model.RedditUrlRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FrequencyControllerTest {

    FrequencyController controller;
    RestTemplate restTemplate;
    WordCounter mockWordCounter;
    RedditUrlRepository redditUrlRepository;
    WordCountRepository mockWordCountRepository;


    @Before
    public void setUp() throws Exception {
        restTemplate = mock(RestTemplate.class);
        mockWordCounter = mock(WordCounter.class);
        redditUrlRepository = mock(RedditUrlRepository.class);
        mockWordCountRepository = mock(WordCountRepository.class);
        controller = new FrequencyController();
        controller.setRestTemplate(restTemplate);
        controller.setWordCounter(mockWordCounter);
        controller.setRedditUrlRepository(redditUrlRepository);
        controller.setWordCountRepository(mockWordCountRepository);
    }

    @Test
    public void testCreateFrequency() throws Exception {
        Collection<WordCount> wordCounts = new ArrayList<>();
        WordCount wordCount1 = new WordCount();
        wordCount1.setWord("cat");
        wordCount1.setCount(3);
        WordCount wordCount2 = new WordCount();
        wordCount2.setWord("dog");
        wordCount2.setCount(7);
        wordCounts.add(wordCount1);
        wordCounts.add(wordCount2);
        when(restTemplate.getForEntity("http://reddit.com", String.class)).thenReturn(new ResponseEntity<>("json", HttpStatus.OK));
        RedditUrl expectedUrl = new RedditUrl();
        expectedUrl.setUrl("http://reddit.com");
        RedditUrl savedUrl = new RedditUrl();
        savedUrl.setId(87L);
        when(redditUrlRepository.save(expectedUrl)).thenReturn(savedUrl);
        when(mockWordCounter.countWords("json")).thenReturn(wordCounts);

        assertEquals(new ResponseEntity(87L, HttpStatus.OK), controller.newFrequency("http://reddit.com"));
        verify(mockWordCountRepository).save(wordCounts);
    }
}