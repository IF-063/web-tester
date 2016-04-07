package com.softserve.webtester.dto;

import java.util.List;

import com.softserve.webtester.model.Request;

public class RequestResultDTO {

    private Request request;
    private RequestDTO requestDTO; // TODO VZ: Rename to preparedRequest or requestToSend
    private List<ResponseDTO> responses;

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