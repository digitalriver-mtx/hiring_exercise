package com.drmtx.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class WordCounter {
    public Collection<WordCount> countWords(String inputJson) throws IOException {
        JsonNode jsonNode = new ObjectMapper().readTree(inputJson);

        List<String> bodies = jsonNode.findValuesAsText("body");
        Map<String, WordCount> wordCounts = new HashMap<>();

        bodies.stream().forEach((words) -> {
            Arrays.stream(words.split(" ")).map((word) -> { return word.toLowerCase().replaceAll("[\\.,\\?!]", ""); }).forEach((word) -> {
                WordCount currentWordCount;
                if (wordCounts.containsKey(word)) {
                    currentWordCount = wordCounts.get(word);
                } else {
                    currentWordCount = new WordCount();
                    currentWordCount.setWord(word);
                    wordCounts.put(word, currentWordCount);
                }
                currentWordCount.incrementCount();
            });
        });

        return wordCounts.values();
    }
}
