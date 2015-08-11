package com.drmtx.app.web;

import com.drmtx.app.service.RedditCommentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class FrequencyController {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private RedditCommentService redditCommentService;

    @RequestMapping("/frequency/new")
    public NewFrequencyResult frequencyNew(@RequestParam(value = "url") String redditUrl) {
        String requestId = redditCommentService.importCommentWordFrequencies(redditUrl);
        return new NewFrequencyResult(requestId);
    }

    @RequestMapping("/frequency/{id}")
    public List<FrequencyQueryResult> frequencyQuery(@PathVariable String id, @RequestParam(value = "count") int count) {
        return redditCommentService.findWordFrequencies(id, count).stream()
                .map(wf -> new FrequencyQueryResult(wf.getTerm(), wf.getTermCount()))
                .collect(toList());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        logger.warn(e.getMessage());
        return "";
    }
}
