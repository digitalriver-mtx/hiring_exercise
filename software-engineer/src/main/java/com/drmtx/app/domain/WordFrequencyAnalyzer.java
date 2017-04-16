package com.drmtx.app.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

/**
 * Analyze the word frequency.
 * <pre>
 * - All whitespace characters should be ignored
 * - All words should be converted to lower case
 * - Following punctuation characters should be ignored: !,.?
 * </pre>
 */
public class WordFrequencyAnalyzer {

    /**
     * Analyze a text for word frequencies.
     *
     * @param text the text to analyze
     * @return the word frequencies
     */
    public Map<String, Integer> analyze(String text) {

        // check for empty input
        if (StringUtils.isBlank(text)) {
            return new HashMap<>();
        }

        // split the text
        List<String> words = Arrays.asList(text.toLowerCase().split("[\\s.,?\\t\\n\\r]"));

        // calculate frequencies
        return words.stream()
                .filter(StringUtils::isNotBlank)
                .collect(groupingBy(identity(), summingInt(e -> 1)));
    }

    /**
     * Analyze multiple texts for word frequencies.
     *
     * @param texts     the texts to analyze
     * @param requestId the  requestId
     * @return the word frequencies
     */
    public List<WordFrequency> analyze(List<String> texts, String requestId) {
        // merge all texts
        String allTexts = texts.stream()
                .collect(joining(" "));

        // analyze word frequencies
        Map<String, Integer> frequencies = analyze(allTexts);

        // map result to model
        return frequencies.entrySet().stream()
                .map(e -> new WordFrequency(requestId, e.getKey(), e.getValue()))
                .collect(toList());
    }
}
