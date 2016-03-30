package com.softserve.webtester.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
    public List<ReportDataDTO> loadReportData(ReportFilterDTO reportFilterDTO) {
        int serviceId = reportFilterDTO.getServiceId();
        int[] buildVersionIds = reportFilterDTO.getBuildVersionId();
        if (reportFilterDTO.getResponseTimeFilterMarker() == 1) {
            return loadWithAvarageResponseTime(serviceId, buildVersionIds);
        } else {
            return loadWithMaxResponseTime(serviceId, buildVersionIds);
        }
    }

    @Transactional
    public int loadAvarageResponseTimeForService(ReportFilterDTO reportFilterDTO) {
        return reportMapper.loadAvarage(reportFilterDTO.getServiceId());
    }

    public List<ReportDataDTO> loadWithAvarageResponseTime(int serviceId, int[] buildVersionIds) {
        try {
            return reportMapper.loadAvg(serviceId, buildVersionIds);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load ResponseTime for request", e);
            throw e;
        }
    }

    @Transactional
    public List<ReportDataDTO> loadWithMaxResponseTime(int serviceId, int[] buildVersionIds) {
        try {
            return reportMapper.loadMax(serviceId, buildVersionIds);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load ResponseTime for request", e);
            throw e;
        }
    }

    @Transactional
    public List<StatisticDataDTO> loadStatisticReportData(StatisticFilterDTO statisticFilterDTO) {
        int[] serviceId = statisticFilterDTO.getServiceId();
        int[] buildVersionIds = statisticFilterDTO.getBuildVersionId();

        List<StatisticDataDTO> result = new ArrayList<>(serviceId.length);
        for (int id : serviceId) {
            StatisticDataDTO dto = reportMapper.loadStatisticDataDTO(id);
            if (statisticFilterDTO.getResponseTimeFilterMarker() == 1) {
                dto.setResponseTimes(loadStatisticWithAverageResponseTime(id, buildVersionIds));
            } else {
                dto.setResponseTimes(loadStatisticWithMaximumResponseTime(id, buildVersionIds));
            }
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

    @Transactional
    public List<Integer> loadStatisticWithMaximumResponseTime(int serviceId, int[] buildVersionIds) {
        try {
            return reportMapper.loadMaxStatistic(serviceId, buildVersionIds);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load ResponseTime for request", e);
            throw e;
        }
    }

    @Transactional
    public List<String> loadBuildVersionsName(StatisticFilterDTO statisticFilterDTO) {
        List<Integer> statisticsBuildVersionsId = IntStream.of(statisticFilterDTO.getBuildVersionId()).boxed()
                .collect(Collectors.toList());
        List<String> statisticsBuildVersions = statisticFilterDTO.getBuildVersions().stream()
                .filter(x -> statisticsBuildVersionsId.contains(x.getId())).map(x -> x.getName())
                .collect(Collectors.toList());
        return statisticsBuildVersions;
    }
}
