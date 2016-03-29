package com.softserve.webtester.dto;

import com.softserve.webtester.model.Environment;

import java.util.List;

public class ResultsDTO {

    private int runId;
    private int buildVersionId;
    private Environment environment;
    private List<RequestResultDTO> requestResults;

    public ResultsDTO() {

    }

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public int getBuildVersionId() {
        return buildVersionId;
    }

    public void setBuildVersionId(int buildVersionId) {
        this.buildVersionId = buildVersionId;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public List<RequestResultDTO> getRequestResults() {
        return requestResults;
    }

    public void setRequestResults(List<RequestResultDTO> requestResults) {
        this.requestResults = requestResults;
    }
}