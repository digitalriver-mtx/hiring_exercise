package com.drmtx.app.web;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * New reequency result json model.
 */
public class NewFrequencyResult {

    @JsonProperty("id")
    private String requestId;

    public NewFrequencyResult(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    @Override
    public String toString() {
        return "NewFrequencyResult{" +
                "requestId='" + requestId + '\'' +
                '}';
    }
}
