package com.drmtx.app.domain;

import com.drmtx.app.domain.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "word_frequency")
public class WordFrequency extends EntityBase {

    @Column(name = "request_id", nullable = true)
    private String requestId;

    @Column(name = "term", nullable = true)
    private String term;

    @Column(name = "term_count", nullable = true)
    private int termCount;

    public WordFrequency() {
    }

    public WordFrequency(String requestId, String term, int termCount) {
        this.requestId = requestId;
        this.term = term;
        this.termCount = termCount;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getTermCount() {
        return termCount;
    }

    public void setTermCount(int termCount) {
        this.termCount = termCount;
    }

    @Override
    public String toString() {
        return "WordFrequency{" +
                "requestId='" + requestId + '\'' +
                ", term='" + term + '\'' +
                ", termCount=" + termCount +
                '}';
    }
}
