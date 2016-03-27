package com.softserve.webtester.dto;

import java.io.Serializable;
import java.util.Arrays;

public class ResultFilterDTO implements Serializable {

    private static final long serialVersionUID = -4420976248741084072L;

    private String statusFilter;
    private int[] applicationFilter;
    private int[] serviceFilter;

    public ResultFilterDTO() { }

    public String getStatusFilter() {
        return statusFilter;
    }

    public void setStatusFilter(String statusFilter) {
        this.statusFilter = statusFilter;
    }

    public int[] getApplicationFilter() {
        return applicationFilter;
    }

    public void setApplicationFilter(int[] applicationFilter) {
        this.applicationFilter = applicationFilter;
    }

    public int[] getServiceFilter() {
        return serviceFilter;
    }

    public void setServiceFilter(int[] serviceFilter) {
        this.serviceFilter = serviceFilter;
    }
}
