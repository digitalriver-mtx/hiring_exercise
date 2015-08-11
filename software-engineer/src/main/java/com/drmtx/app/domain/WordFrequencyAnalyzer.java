package com.drmtx.app.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * Analyze the word frequency.
 * <pre>
 * - All whitespace characters should be ignored
 * - All words should be converted to lower case
 * - Following punctuation characters should be ignored: !,.?
 * </pre>
 */
public class WordFrequencyAnalyzer {

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
                .collect(groupingBy(Function.identity(), summingInt(e -> 1)));
    }
}
