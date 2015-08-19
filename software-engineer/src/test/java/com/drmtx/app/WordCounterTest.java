package com.drmtx.app;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class WordCounterTest {

    WordCounter wordCounter;

    @Before
    public void setUp() throws Exception {
        wordCounter = new WordCounter();
    }

    @Test
    public void testCountFromJson_noWords() throws Exception {

        String input = "[" +
                "  {" +
                "    \"data\": {" +
                "      \"children\": [" +
                "        {" +
                "          \"data\": {}" +
                "        }" +
                "      ]" +
                "    }" +
                "  }" +
                "]";

        Collection<WordCount> wordCounts = wordCounter.countWords(input);

        assertEquals(0, wordCounts.size());
    }

    @Test
    public void testCountFromJson() throws Exception {
        String input = "[" +
                "  {" +
                "    \"data\": {" +
                "    \"body\": \"Digital River Inc. digital DIGITAL\"" +
                "    }" +
                "  }" +
                "]";

        WordCount expectedCount1 = new WordCount();
        expectedCount1.setWord("digital");
        expectedCount1.setCount(3);
        WordCount expectedCount2 = new WordCount();
        expectedCount2.setWord("river");
        expectedCount2.setCount(1);
        WordCount expectedCount3 = new WordCount();
        expectedCount3.setWord("inc");
        expectedCount3.setCount(1);

        Collection<WordCount> wordCounts = wordCounter.countWords(input);

        assertEquals(3, wordCounts.size());
        assertTrue(wordCounts.contains(expectedCount1));
        assertTrue(wordCounts.contains(expectedCount2));
        assertTrue(wordCounts.contains(expectedCount3));
    }

    @Test
    public void testCountFromJson_whitespace() throws Exception {
        String input = "[" +
                "  {" +
                "    \"data\": {" +
                "    \"body\": \"Digital    River\\tInc\"" +
                "    }" +
                "  }" +
                "]";

        WordCount expectedCount1 = new WordCount();
        expectedCount1.setWord("digital");
        expectedCount1.setCount(1);
        WordCount expectedCount2 = new WordCount();
        expectedCount2.setWord("river");
        expectedCount2.setCount(1);
        WordCount expectedCount3 = new WordCount();
        expectedCount3.setWord("inc");
        expectedCount3.setCount(1);

        Collection<WordCount> wordCounts = wordCounter.countWords(input);

        assertEquals(3, wordCounts.size());
        assertTrue(wordCounts.contains(expectedCount1));
        assertTrue(wordCounts.contains(expectedCount2));
        assertTrue(wordCounts.contains(expectedCount3));
    }

    @Test
    public void testCountFromChildren() throws Exception {
        String input = "{" +
                "  \"kind\": \"Listing\"," +
                "  \"data\": { " +
                "    \"body\": \"foo\"," +
                "    \"children\": [" +
                "      {" +
                "        \"data\": {" +
                "          \"replies\": {" +
                "            \"data\": {" +
                "              \"children\": [" +
                "                {" +
                "                  \"data\": {" +
                "                    \"body\": \"foo bar baz\"" +
                "                  }" +
                "                }" +
                "              ]" +
                "            }" +
                "          }" +
                "        }" +
                "      }" +
                "    ]" +
                "  }" +
                "}";

        WordCount expectedCount1 = new WordCount();
        expectedCount1.setWord("foo");
        expectedCount1.setCount(2);
        WordCount expectedCount2 = new WordCount();
        expectedCount2.setWord("bar");
        expectedCount2.setCount(1);
        WordCount expectedCount3 = new WordCount();
        expectedCount3.setWord("baz");
        expectedCount3.setCount(1);

        Collection<WordCount> wordCounts = wordCounter.countWords(input);

        assertEquals(3, wordCounts.size());
        assertTrue(wordCounts.contains(expectedCount1));
        assertTrue(wordCounts.contains(expectedCount2));
        assertTrue(wordCounts.contains(expectedCount3));
    }
}