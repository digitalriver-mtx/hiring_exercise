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
    private int count = 0;

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

    public void incrementCount() {
        count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordCount wordCount = (WordCount) o;

        if (count != wordCount.count) return false;
        if (id != null ? !id.equals(wordCount.id) : wordCount.id != null) return false;
        if (urlId != null ? !urlId.equals(wordCount.urlId) : wordCount.urlId != null) return false;
        return !(word != null ? !word.equals(wordCount.word) : wordCount.word != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (urlId != null ? urlId.hashCode() : 0);
        result = 31 * result + (word != null ? word.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }
}
