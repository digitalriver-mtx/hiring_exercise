package com.drmtx.app.repository;

import com.drmtx.app.Application;
import com.drmtx.app.domain.WordFrequency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
public class WordFrequencyIntegrationTest {

    private final static String SOME_TERM = "someTerm";
    private final static int SOME_TERM_COUNT = 42;
    private final static int SOME_OTHER_TERM_COUNT = 21;

    @Autowired
    private WordFrequencyRepository wordFrequencyRepository;

    @Test
    public void validateCRUD() {
        String id = UUID.randomUUID().toString();
        WordFrequency writeWordFrequency = new WordFrequency(id, SOME_TERM, SOME_TERM_COUNT);

        // validate no entities for the id is found
        assertThat(wordFrequencyRepository.findByRequestIdOrderByTermCountDesc(id, new PageRequest(0, 100)).size(), is(0));

        // create
        wordFrequencyRepository.save(writeWordFrequency);

        // read
        List<WordFrequency> readWordFrequencies = wordFrequencyRepository.findByRequestIdOrderByTermCountDesc(id, new PageRequest(0, 100));
        assertThat(readWordFrequencies.size(), is(1));

        WordFrequency readWordFrequency = readWordFrequencies.get(0);
        assertThat(readWordFrequency.getRequestId(), is(id));
        assertThat(readWordFrequency.getTerm(), equalTo(SOME_TERM));
        assertThat(readWordFrequency.getTermCount(), is(SOME_TERM_COUNT));

        //update
        readWordFrequency.setTermCount(SOME_OTHER_TERM_COUNT);
        wordFrequencyRepository.save(readWordFrequency);

        readWordFrequencies = wordFrequencyRepository.findByRequestIdOrderByTermCountDesc(id, new PageRequest(0, 100));
        assertThat(readWordFrequencies.size(), is(1));

        readWordFrequency = readWordFrequencies.get(0);
        assertThat(readWordFrequency.getRequestId(), is(id));
        assertThat(readWordFrequency.getTerm(), equalTo(SOME_TERM));
        assertThat(readWordFrequency.getTermCount(), is(SOME_OTHER_TERM_COUNT));

        // delete
        wordFrequencyRepository.delete(readWordFrequency);

        assertThat(wordFrequencyRepository.findByRequestIdOrderByTermCountDesc(id, new PageRequest(0, 100)).size(), is(0));
    }
}
