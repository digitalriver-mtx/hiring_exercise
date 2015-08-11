package com.drmtx.app.domain;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.Is.is;

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
}
