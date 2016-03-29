package com.softserve.webtester.dto;

import com.softserve.webtester.model.Request;

import java.util.List;

public class RequestResultDTO {

    private int collectionId;
    private List<Request> requests;
    private List<ResponseDTO> responses;

    public RequestResultDTO() {

    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<ResponseDTO> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseDTO> responses) {
        this.responses = responses;
    }
}