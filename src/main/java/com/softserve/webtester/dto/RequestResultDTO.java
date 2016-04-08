package com.softserve.webtester.dto;

import java.util.List;

import com.softserve.webtester.model.Request;

public class RequestResultDTO {

    private Request request;
    private PreparedRequestDTO preparedRequestDTO;
    private List<ResponseDTO> responses;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public PreparedRequestDTO getPreparedRequestDTO() {
        return preparedRequestDTO;
    }

    public void setPreparedRequestDTO(PreparedRequestDTO preparedRequestDTO) {
        this.preparedRequestDTO = preparedRequestDTO;
    }

    public List<ResponseDTO> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseDTO> responses) {
        this.responses = responses;
    }
}