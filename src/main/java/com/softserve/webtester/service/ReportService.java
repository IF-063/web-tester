package com.softserve.webtester.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.webtester.dto.ReportDataDTO;
import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.dto.StatisticDataDTO;
import com.softserve.webtester.dto.StatisticFilterDTO;
import com.softserve.webtester.mapper.ReportMapper;

@Service
public class ReportService {

    private static final Logger LOGGER = Logger.getLogger(ReportService.class);

    @Autowired
    private ReportMapper reportMapper;

    @Transactional
    public List<ReportDataDTO> loadReportData(ReportFilterDTO reportFilterDTO){
        int serviceId = reportFilterDTO.getServiceId();
        int [] buildVersionIds = reportFilterDTO.getBuildVersionId();
        if (reportFilterDTO.getResponseTimeFilterMarker()==1){
            return loadWithAvarageResponseTime(serviceId, buildVersionIds);
        }
        else {
            return loadWithMaxResponseTime(serviceId, buildVersionIds);
        }   
    }
    
    @Transactional
    public int loadAvarageResponseTimeForService(ReportFilterDTO reportFilterDTO){
        return reportMapper.loadAvarage(reportFilterDTO.getServiceId());
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

    
    
    
    @Transactional
    public List<StatisticDataDTO> loadStatisticReportData(StatisticFilterDTO reportFilterDTO){
        int[] serviceId = reportFilterDTO.getServiceId();
        int[] buildVersionIds = reportFilterDTO.getBuildVersionId();
        
        List<StatisticDataDTO> result = new ArrayList<>(serviceId.length);
        for (int id : serviceId) {
            StatisticDataDTO dto =reportMapper.loadStatisticDataDTO(id);
            dto.setResponseTimes(loadStatisticWithAverageResponseTime(id, buildVersionIds));
            result.add(dto);
        }
        return result;
    }
    
    @Transactional
    public List<Integer> loadStatisticWithAverageResponseTime(int serviceId, int[] buildVersionIds) {
        try {
            return reportMapper.loadAvgStatistic(serviceId, buildVersionIds);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load ResponseTime for request", e);
            throw e;
        }
    }
}