package com.softserve.webtester.service;

import com.softserve.webtester.dto.ReportFinalData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.softserve.webtester.dto.ReportDataDTO;
import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.mapper.ReportMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private static final Logger LOGGER = Logger.getLogger(ReportService.class);

    @Autowired
    private ReportMapper reportMapper;

    @Transactional
    public List<ReportDataDTO> loadGraphicData(ReportFilterDTO reportFilterDTO){

        return loadFinalData(reportFilterDTO);
    }

    private List<ReportDataDTO> loadFinalData(ReportFilterDTO reportFilterDTO){
        int serviceId = reportFilterDTO.getServiceId();
        int [] buildVersionIds = reportFilterDTO.getBuildVersionId();
        int responseTimeFilterMarker = reportFilterDTO.getResponseTimeFilterMarker();
        if (responseTimeFilterMarker==1){
            return loadWithAvarageResponseTime(serviceId, buildVersionIds);
        }
        else {
            return loadWithMaxResponseTime(serviceId, buildVersionIds);
        }
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
