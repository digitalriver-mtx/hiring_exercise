package com.drmtx.app.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FrequencyQueryResult {

    @JsonProperty("word")
    private String word;

    @JsonProperty("count")
    private int count;

    public FrequencyQueryResult(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "FrequencyQueryResult{" +
                "word='" + word + '\'' +
                ", count=" + count +
                '}';
    }
}
