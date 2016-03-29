package com.softserve.webtester.dto;

import java.io.Serializable;

public class ResultCollectionFilterDTO implements Serializable {

    private static final long serialVersionUID = -3133417511946682972L;

    private String statusFilter;
    private int[] buildVersionsFilter;
    private int[] labelFilter;

    public ResultCollectionFilterDTO() { }

    public String getStatusFilter() {
        return statusFilter;
    }

    public void setStatusFilter(String statusFilter) {
        this.statusFilter = statusFilter;
    }

    public int[] getBuildVersionsFilter() {
        return buildVersionsFilter;
    }

    public void setBuildVersionsFilter(int[] buildVersionsFilter) {
        this.buildVersionsFilter = buildVersionsFilter;
    }

    public int[] getLabelFilter() {
        return labelFilter;
    }

    public void setLabelFilter(int[] labelFilter) {
        this.labelFilter = labelFilter;
    }
}