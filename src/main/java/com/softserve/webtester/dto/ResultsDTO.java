package com.softserve.webtester.dto;

import com.softserve.webtester.model.Environment;

import java.util.List;

public class ResultsDTO {

    private int runId;
    private int buildVersionId;
    private Environment environment;
    private List<CollectionResultDTO> collectionResultDTOList;

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

    public List<CollectionResultDTO> getCollectionResultDTOList() {
        return collectionResultDTOList;
    }

    public void setCollectionResultDTOList(List<CollectionResultDTO> collectionResultDTOList) {
        this.collectionResultDTOList = collectionResultDTOList;
    }
}