package com.drmtx.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FrequencyController {

    @Autowired
    WordCountRepository wordCountRepository;

    @RequestMapping(value = "/frequency/new", method = RequestMethod.GET)
    public ResponseEntity<Long> newFrequency() {
        return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/frequency/{urlId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map>> getFrequencies(@PathVariable Long urlId) {
        Page<WordCount> wordCounts = wordCountRepository.findAllByUrlId(urlId, new PageRequest(0, 100));

        if (wordCounts.getTotalElements() == 0) {
            return new ResponseEntity<List<Map>>(HttpStatus.NOT_FOUND);
        }

        List<Map> counts = new ArrayList<>();
        for(WordCount wordCount : wordCounts) {
            Map currentMap = new HashMap<String, Number>();
            currentMap.put("word", wordCount.getWord());
            currentMap.put("count", wordCount.getCount());
            counts.add(currentMap);
        }

        return new ResponseEntity<List<Map>>(counts, HttpStatus.OK);
    }
}
