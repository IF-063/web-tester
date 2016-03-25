package com.softserve.webtester.service;

import com.softserve.webtester.dto.ReportFinalData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.softserve.webtester.dto.ReportDataDTO;
import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.mapper.ReportMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    
    private static final Logger LOGGER = Logger.getLogger(ReportService.class);
    
    @Autowired
    private ReportMapper reportMapper;

    public ReportFinalData loadGraphicData(ReportFilterDTO reportFilterDTO){

        List<ReportDataDTO> list = loadResponseTime(reportFilterDTO);
        int[] times = new int[]{};
        String[] buildVersions = new String[]{};
        int timeMarker = reportFilterDTO.getResponseTimeFilterMarker();

        for(int i=0;i<list.size();i++){
            times[i] = list.get(i).getResponseTime();
            buildVersions[i] = list.get(i).getBuildVersionName();
        }
        return new ReportFinalData(timeMarker, times, buildVersions);
    }

    private List<ReportDataDTO> loadResponseTime(ReportFilterDTO reportFilterDTO){

        int serviceId = reportFilterDTO.getServiceId();
        int[] buildVersionIds = reportFilterDTO.getBuildVersionId();
        int responseTimeFilterMarker = reportFilterDTO.getResponseTimeFilterMarker();

        List<ReportDataDTO> list = new ArrayList<>();
        if (responseTimeFilterMarker==1 && serviceId!=0 && buildVersionIds.length>1){
            list = loadWithAvarageResponseTime(serviceId, buildVersionIds);
        }

        else if (responseTimeFilterMarker==2 && serviceId!=0 && buildVersionIds.length>1){
            list = loadWithMaxResponseTime(serviceId, buildVersionIds);
        }
        return list;
    }

    private List<ReportDataDTO> loadWithAvarageResponseTime(int serviceId, int[] buildVersionIds) {
        try {
            return reportMapper.loadAvg(serviceId, buildVersionIds);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load ResponseTime for request", e);
            throw e;
        }
    }

    private List<ReportDataDTO> loadWithMaxResponseTime(int serviceId, int[] buildVersionIds) {
        try {
            return reportMapper.loadMax(serviceId, buildVersionIds);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load ResponseTime for request", e);
            throw e;
        }
    }
}
    
    
       
