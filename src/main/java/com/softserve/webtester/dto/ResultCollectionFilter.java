package com.softserve.webtester.dto;

import java.io.Serializable;

public class ResultCollectionFilter implements Serializable {

    private static final long serialVersionUID = -9072422011057426510L;

    private String statusFilter;
    private Integer buildVersionFilter;
    private int[] labelFilter;

    public ResultCollectionFilter() { }

    public String getStatusFilter() {
        return statusFilter;
    }

    public void setStatusFilter(String statusFilter) {
        this.statusFilter = statusFilter;
    }

    public Integer getBuildVersionFilter() {
        return buildVersionFilter;
    }

    public void setBuildVersionFilter(Integer buildVersionFilter) {
        this.buildVersionFilter = buildVersionFilter;
    }

    public int[] getLabelFilter() {
        return labelFilter;
    }

    public void setLabelFilter(int[] labelFilter) {
        this.labelFilter = labelFilter;
    }
}