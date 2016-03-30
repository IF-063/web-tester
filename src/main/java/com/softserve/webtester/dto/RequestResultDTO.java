package com.softserve.webtester.dto;

import com.softserve.webtester.model.Request;

import java.util.List;

public class RequestResultDTO {

    private Request request;
    private RequestDTO requestDTO;
    private List<ResponseDTO> responses;

    public RequestResultDTO() {

    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public RequestDTO getRequestDTO() {
        return requestDTO;
    }

    public void setRequestDTO(RequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }

    public List<ResponseDTO> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseDTO> responses) {
        this.responses = responses;
    }
}