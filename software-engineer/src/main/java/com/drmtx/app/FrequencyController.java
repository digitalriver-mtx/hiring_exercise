package com.drmtx.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class FrequencyController {

    @RequestMapping(value = "/frequency/new", method = RequestMethod.GET)
    public ResponseEntity<Map> newFrequency() {
        return new ResponseEntity<Map>(HttpStatus.BAD_REQUEST);
    }
}
