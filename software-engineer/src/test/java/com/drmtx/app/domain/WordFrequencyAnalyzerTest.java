package com.drmtx.app.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class WordFrequencyAnalyzerTest {

    @Test
    public void validatExpectedFrequencies() {
        WordFrequencyAnalyzer wordFrequencyAnalyzer = new WordFrequencyAnalyzer();

        Map<String, Integer> wordFrequencies = wordFrequencyAnalyzer.analyze("The quick \n brown fox \t jumps over the lazy dog.");

        assertThat(wordFrequencies.entrySet().size(), is(8));

        assertThat(wordFrequencies, hasEntry("the", 2));
        assertThat(wordFrequencies, hasEntry("quick", 1));
        assertThat(wordFrequencies, hasEntry("brown", 1));
        assertThat(wordFrequencies, hasEntry("fox", 1));
        assertThat(wordFrequencies, hasEntry("jumps", 1));
        assertThat(wordFrequencies, hasEntry("over", 1));
        assertThat(wordFrequencies, hasEntry("lazy", 1));
        assertThat(wordFrequencies, hasEntry("dog", 1));
    }

    @Test
    public void validateExpectedMappedFrequencies() {
        WordFrequencyAnalyzer wordFrequencyAnalyzer = new WordFrequencyAnalyzer();

        String id = UUID.randomUUID().toString();

        List<WordFrequency> wordFrequencies = wordFrequencyAnalyzer.analyze(Arrays.asList("The", "the"), id);

        assertThat(wordFrequencies.size(), is(1));

        WordFrequency wordFrequency = wordFrequencies.get(0);
        assertThat(wordFrequency.getRequestId(), is(id));
        assertThat(wordFrequency.getTerm(), equalTo("the"));
        assertThat(wordFrequency.getTermCount(), is(2));
    }
}
