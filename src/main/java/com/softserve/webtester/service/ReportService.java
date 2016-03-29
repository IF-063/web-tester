package com.softserve.webtester.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

    private HSSFWorkbook workbook;

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

    public void GenerateExcelReport(StatisticFilterDTO statisticFilterDTO) {
        int k;
        List<String> buildVersionName = loadBuildVersionsName(statisticFilterDTO);
        List<StatisticDataDTO> statisticData = loadStatisticReportData(statisticFilterDTO);
        workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("StatisticReport");
        HSSFRow row = spreadsheet.createRow(0);
        row.createCell(0).setCellValue("Service Name");
        row.createCell(1).setCellValue("SLA");
        for (k = 0; k < buildVersionName.size(); k++) {
            row.createCell(2 + k).setCellValue(buildVersionName.get(k));
        }
        row.createCell(2 + k).setCellValue("Average for the last three releases");
        for (int i = 0; i < statisticData.size(); i++) {
            int j;
            row = spreadsheet.createRow(i + 1);
            row.createCell(0).setCellValue(statisticData.get(i).getServiceName());
            row.createCell(1).setCellValue(statisticData.get(i).getSla());
            for (j = 0; j < buildVersionName.size(); j++) {
                row.createCell(j + 2).setCellValue(statisticData.get(i).getResponseTimes().get(j));
            }
            row.createCell(2 + j).setCellValue(statisticData.get(i).getAverageResponseTime());
        }
        // Create file system using specific name
        try {
            FileOutputStream out = new FileOutputStream(new File("createworkbook.xlsx"));
            // write operation workbook using file out object
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
