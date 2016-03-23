package com.softserve.webtester.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.softserve.webtester.dto.ReportDataDTO;
import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.mapper.ReportMapper;

@Service
public class ReportService {
    
    private static final Logger LOGGER = Logger.getLogger(ReportService.class);
    
    @Autowired
    private ReportMapper reportMapper;
    
    public ReportDataDTO loadWithAvarageResponseTime(ReportFilterDTO reportFilterDTO) {
        try {
            return reportMapper.loadAVG(reportFilterDTO.getServiceId(), reportFilterDTO.getBuildVersionId());
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load ResponseTime for request", e);
            throw e;
        }
    }  
    
    public ReportDataDTO loadWithMaximumResponseTime(ReportFilterDTO reportFilterDTO) {
        try {
            return reportMapper.loadMAX(reportFilterDTO.getServiceId(), reportFilterDTO.getBuildVersionId());
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load ResponseTime for request", e);
            throw e;
        }
    }
}
    
    
       
