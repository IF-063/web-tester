package com.softserve.webtester.dto;

import org.apache.http.HttpResponse;

public class ResponseDTO {

    private long responseTime;
    private HttpResponse response;

    public ResponseDTO() {

    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }
}