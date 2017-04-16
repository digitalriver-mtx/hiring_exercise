package com.drmtx.app.service;

import com.drmtx.app.domain.WordFrequency;
import com.drmtx.app.domain.WordFrequencyAnalyzer;
import com.drmtx.app.domain.reddit.RedditComment;
import com.drmtx.app.repository.RedditRepository;
import com.drmtx.app.repository.WordFrequencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RedditCommentService {

    @Autowired
    private RedditRepository redditRepository;
    @Autowired
    private WordFrequencyRepository wordFrequencyRepository;

    public String importCommentWordFrequencies(String redditUrl) {
        // generate request id
        String requestId = UUID.randomUUID().toString();

        // query comments from reddit
        RedditComment redditComment = redditRepository.findCommentByUrl(redditUrl);

        // analyze word frequencies
        WordFrequencyAnalyzer wordFrequencyAnalyzer = new WordFrequencyAnalyzer();
        List<WordFrequency> wordFrequencies = wordFrequencyAnalyzer.analyze(redditComment.bodies(), requestId);

        // persist word frequencies
        wordFrequencyRepository.save(wordFrequencies);

        return requestId;
    }

    public List<WordFrequency> findWordFrequencies(String requestId, int maximumElements) {
        return wordFrequencyRepository.findByRequestIdOrderByTermCountDesc(requestId, new PageRequest(0, maximumElements));
    }
}
