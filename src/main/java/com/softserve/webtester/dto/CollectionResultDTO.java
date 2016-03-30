package com.softserve.webtester.dto;

import java.util.List;

public class CollectionResultDTO {

    private int collectionId;
    private List<RequestResultDTO> requestResultDTOList;

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public List<RequestResultDTO> getRequestResultDTOList() {
        return requestResultDTOList;
    }

    public void setRequestResultDTOList(List<RequestResultDTO> requestResultDTOList) {
        this.requestResultDTOList = requestResultDTOList;
    }
}
