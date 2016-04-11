package com.softserve.webtester.service;

import com.softserve.webtester.dto.ReportDataDTO;
import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.mapper.ReportMapper;
import com.softserve.webtester.model.ResponseTimeType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 *  Unit testing of ReportService class with mock objects
 */
@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

    @Mock
    ReportMapper reportMapper;

    @InjectMocks
    private ReportService reportService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Using a mock ReportMapper object for testing loadWithAvarageResponseTimeTest() method that consists of
     * setting expectations on the mock object with assertEquals statement and
     * doing the verification of the method calls made to a mock object with verify statement
     */
    @Test
    public void loadWithAvarageResponseTimeTest() {

        int serviceId = 2;
        int[] buildVersionIds = {1, 4};
        List<ReportDataDTO> expectedReportDataDTO = new ArrayList<>();
        expectedReportDataDTO.add(new ReportDataDTO("s2", 60, "v1.0"));
        expectedReportDataDTO.add(new ReportDataDTO("s2", 80, "v1.1"));

        when(reportMapper.loadAvg(serviceId, buildVersionIds)).thenReturn(expectedReportDataDTO);
        assertEquals(expectedReportDataDTO, reportMapper.loadAvg(serviceId, buildVersionIds));

        verify(reportMapper, times(1)).loadAvg(serviceId, buildVersionIds);
        verify(reportMapper, times(0)).loadMax(serviceId, buildVersionIds);
    }

    /**
     * Using a mock ReportMapper object for testing loadWithAvarageResponseTimeTest() method that consists of
     * setting expectations on the mock object with assertEquals statement and
     * doing the verification of the method calls made to a mock object with verify statement
     */
    @Test
    public void loadWithMaxResponseTimeTest() {

        int serviceId = 2;
        int[] buildVersionIds = {1, 4};
        List<ReportDataDTO> expectedReportDataDTO = new ArrayList<>();
        expectedReportDataDTO.add(new ReportDataDTO("s2", 70, "v1.0"));
        expectedReportDataDTO.add(new ReportDataDTO("s2", 90, "v1.1"));

        when(reportMapper.loadMax(serviceId, buildVersionIds)).thenReturn(expectedReportDataDTO);
        assertEquals(expectedReportDataDTO, reportMapper.loadMax(serviceId, buildVersionIds));
        verify(reportMapper, times(1)).loadMax(serviceId, buildVersionIds);
        verify(reportMapper, times(0)).loadAvg(serviceId, buildVersionIds);
    }

    @Test
    public void loadReportDataAvgTest() {

        int serviceId = 2;
        int[] buildVersionIds = {1, 4};
        ResponseTimeType responseTimeFilterMarker = ResponseTimeType.AVERAGE;
        ReportFilterDTO reportFilterDTO = new ReportFilterDTO(serviceId, buildVersionIds, responseTimeFilterMarker);

        List<ReportDataDTO> expectedReportDataDTO = new ArrayList<>();
        expectedReportDataDTO.add(new ReportDataDTO("s2", 60, "v1.0"));
        expectedReportDataDTO.add(new ReportDataDTO("s2", 80, "v1.1"));

        when(reportService.loadReportData(reportFilterDTO)).thenReturn(expectedReportDataDTO);
        assertEquals(expectedReportDataDTO, reportService.loadReportData(reportFilterDTO));
    }

    @Test
    public void loadReportDataTestMaxTest() {

        int serviceId = 2;
        int[] buildVersionIds = {1, 4};
        ResponseTimeType responseTimeFilterMarker = ResponseTimeType.MAX;
        ReportFilterDTO reportFilterDTO = new ReportFilterDTO(serviceId, buildVersionIds, responseTimeFilterMarker);

        List<ReportDataDTO> expectedReportDataDTO = new ArrayList<>();
        expectedReportDataDTO.add(new ReportDataDTO("s2", 70, "v1.0"));
        expectedReportDataDTO.add(new ReportDataDTO("s2", 90, "v1.1"));

        when(reportService.loadReportData(reportFilterDTO)).thenReturn(expectedReportDataDTO);
        assertEquals(expectedReportDataDTO, reportService.loadReportData(reportFilterDTO));
    }
}