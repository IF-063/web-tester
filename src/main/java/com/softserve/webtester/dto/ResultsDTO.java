package com.softserve.webtester.dto;

import java.util.List;

import com.softserve.webtester.model.Environment;
// TODO AM: add java doc
public class ResultsDTO {

    private int runId;
    private int buildVersionId;
    private Environment environment;
    private List<CollectionResultDTO> collectionResultDTOList;

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