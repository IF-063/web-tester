package com.softserve.webtester.dto;

import java.io.Serializable;
import java.util.Arrays;


public class ReportFinalData implements Serializable {

    private static final long serialVersionUID = -3515238733351663442L;

    private int responseTimeFilterMarker;
    private int[] responseTimes;
    private String[] buildVersionNames;

    public ReportFinalData(int responseTimeFilterMarker, int[] responseTimes, String[] buildVersionNames) {
        this.responseTimeFilterMarker = responseTimeFilterMarker;
        this.responseTimes = responseTimes;
        this.buildVersionNames = buildVersionNames;
    }

    public int getResponseTimeFilterMarker() {
        return responseTimeFilterMarker;
    }

    public void setResponseTimeFilterMarker(int responseTimeFilterMarker) {
        this.responseTimeFilterMarker = responseTimeFilterMarker;
    }

    public int[] getResponseTimes() {
        return responseTimes;
    }

    public void setResponseTimes(int[] responseTimes) {
        this.responseTimes = responseTimes;
    }

    public String[] getBuildVersionNames() {
        return buildVersionNames;
    }

    public void setBuildVersionNames(String[] buildVersionNames) {
        this.buildVersionNames = buildVersionNames;
    }

    @Override
    public String toString() {
        return "ReportFinalData{" +
                "responseTimeFilterMarker=" + responseTimeFilterMarker +
                ", responseTimes=" + Arrays.toString(responseTimes) +
                ", buildVersionNames=" + Arrays.toString(buildVersionNames) +
                '}';
    }
}