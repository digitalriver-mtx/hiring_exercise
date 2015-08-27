package com.drmtx.controller;

import com.drmtx.frequency.Frequency;
import com.drmtx.frequency.FrequencyFactory;
import com.drmtx.frequency.Point;
import com.drmtx.service.FrequencyService;
import com.drmtx.util.SelectUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

/**
 * Created by antivo on 8/27/15.
 */
@RestController
@RequestMapping("/frequency")
public class FrequencyController {
    private static final Logger logger = Logger.getLogger(FrequencyController.class);

    @Autowired
    private FrequencyFactory frequencyFactory;

    @Autowired
    private FrequencyService frequencyService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> newFrequency(@RequestBody String urlCoded) {
        try {
            String url = java.net.URLDecoder.decode(urlCoded, "UTF-8");
            System.out.println(url);
            Optional<Frequency> frequency = frequencyFactory.makeFrequency(url);
            if (frequency.isPresent()) {
                System.out.println(url);
                Frequency f = frequencyService.save(frequency.get());
                System.out.println(frequency);
                System.out.println("iz baze" + frequencyService.findOne(f.getId()));
                System.out.println(f.getId());
                return ResponseEntity.ok(f.getId());
            }
        } catch(UnsupportedEncodingException e) {
            // TODO log
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{id}/count", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Point>> count(@PathVariable Long id, @RequestParam Long number_of_results) {
        System.out.println(id);
        Frequency frequency = frequencyService.findOne(id);
        System.out.println(frequency);
        if(null != id) {
            List<Point> list = SelectUtil.selectFirst(number_of_results, frequency.getFrequency());
            return ResponseEntity.ok(list);
        } else {
            return new ResponseEntity<List<Point>>(HttpStatus.NOT_FOUND);
        }

    }



}
