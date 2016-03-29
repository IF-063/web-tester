package com.softserve.webtester.dto;

import com.softserve.webtester.model.Request;
import org.apache.http.HttpResponse;

import java.util.List;

public class ResponseDTO {

    private List<Long> responseTime;
    private Request request;
    private List<HttpResponse> response;

    public ResponseDTO() {

    }

    public List<Long> getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(List<Long> responseTime) {
        this.responseTime = responseTime;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<HttpResponse> getResponse() {
        return response;
    }

    public void setResponse(List<HttpResponse> response) {
        this.response = response;
    }
}
