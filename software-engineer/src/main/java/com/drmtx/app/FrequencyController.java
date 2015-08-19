package com.drmtx.app;

import com.drmtx.app.model.RedditUrl;
import com.drmtx.app.model.RedditUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Controller
public class FrequencyController {

    @Autowired
    WordCountRepository wordCountRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WordCounter wordCounter;

    @Autowired
    private RedditUrlRepository redditUrlRepository;

    @RequestMapping(value = "/frequency/new", method = RequestMethod.POST)
    public ResponseEntity<Long> newFrequency(@RequestParam String url) throws IOException {
        ResponseEntity<String> json = restTemplate.getForEntity(url, String.class);

        Collection<WordCount> wordCounts = wordCounter.countWords(json.getBody());

        RedditUrl redditUrl = new RedditUrl();
        redditUrl.setUrl(url);
        final RedditUrl savedRedditUrl = redditUrlRepository.save(redditUrl);

        wordCounts.stream().forEach((wordCount -> wordCount.setUrlId(savedRedditUrl.getId())));

        wordCountRepository.save(wordCounts);

        return new ResponseEntity<>(savedRedditUrl.getId(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/frequency/{urlId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map>> getFrequencies(@PathVariable Long urlId, @RequestParam() int count) {

        if (count < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Page<WordCount> wordCounts = wordCountRepository.findAllByUrlId(urlId, new PageRequest(0, count, new Sort(Sort.Direction.DESC, "count")));

        if (wordCounts.getTotalElements() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Map> counts = new ArrayList<>();
        for(WordCount wordCount : wordCounts) {
            Map currentMap = new HashMap<String, Number>();
            currentMap.put("word", wordCount.getWord());
            currentMap.put("count", wordCount.getCount());
            counts.add(currentMap);
        }

        return new ResponseEntity<>(counts, HttpStatus.OK);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setWordCounter(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    public void setRedditUrlRepository(RedditUrlRepository redditUrlRepository) {
        this.redditUrlRepository = redditUrlRepository;
    }

    public void setWordCountRepository(WordCountRepository wordCountRepository) {
        this.wordCountRepository = wordCountRepository;
    }
}
