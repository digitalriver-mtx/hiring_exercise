package com.drmtx.app;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class WordCount implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long urlId;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private int count;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }
}
