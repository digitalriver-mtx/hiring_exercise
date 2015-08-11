package com.drmtx.app.web;

import com.drmtx.app.service.RedditCommentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FrequencyController {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private RedditCommentService redditCommentService;

    @RequestMapping("/frequency/new")
    public NewFrequencyResult greeting(@RequestParam(value = "url") String redditUrl) {
        String requestId = redditCommentService.importCommentWordFrequencies(redditUrl);
        return new NewFrequencyResult(requestId);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        logger.warn(e.getMessage());
        return "";
    }
}
