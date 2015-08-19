package com.drmtx.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class ApplicationIntegrationTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    WordCountRepository wordCountRepository;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFrequencyEndpoint_noUrl() throws Exception {
        mockMvc.perform(post("/frequency/new"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testFrequencyEndpoint() throws Exception {
        mockMvc.perform(post("/frequency/new?url=https://www.reddit.com/r/java/comments/32pj67/java_reference_in_gta_v_beautiful/.json", "")).andExpect(status().isCreated());
    }

    @Test
    public void testFrequencyByIdEndpoint_invalidId() throws Exception {
        mockMvc.perform(get("/frequency/1?count=10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFrequencyByIdEndpoint() throws Exception {

        WordCount wordCount1 = new WordCount();
        wordCount1.setUrlId(88L);
        wordCount1.setWord("digital");
        wordCount1.setCount(7);

        WordCount wordCount2 = new WordCount();
        wordCount2.setUrlId(88L);
        wordCount2.setWord("river");
        wordCount2.setCount(2);

        wordCountRepository.save(wordCount1);
        wordCountRepository.save(wordCount2);

        mockMvc.perform(get("/frequency/88?count=3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[ { \"word\": \"digital\", \"count\": 7 }, { \"word\": \"river\", \"count\": 2 } ]"));

        mockMvc.perform(get("/frequency/88?count=2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[ { \"word\": \"digital\", \"count\": 7 }, { \"word\": \"river\", \"count\": 2 } ]"));

        mockMvc.perform(get("/frequency/88?count=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[ { \"word\": \"digital\", \"count\": 7 } ]"));

        mockMvc.perform(get("/frequency/88?count=0"))
                .andExpect(status().isBadRequest());
    }
}