package com.softserve.webtester.service;

import com.softserve.webtester.dto.ReportDataDTO;
import com.softserve.webtester.mapper.ReportMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

    ReportService reportService = new ReportService();
    @Mock
    ReportMapper reportMapper;

    @Before
    public void init (){
        reportService.setReportMapper(reportMapper);
    }

    @Test
    public void loadWithAvarageResponseTimeTest() {

        int serviceId = 2;
        int[] buildVersionIds = {1, 4, 5, 6};

        List<ReportDataDTO> expectedReportDataDTO = new ArrayList<>();
        expectedReportDataDTO.add(new ReportDataDTO("s2", 60, "v1.0"));
        expectedReportDataDTO.add(new ReportDataDTO("s2", 80, "v1.1"));
        expectedReportDataDTO.add(new ReportDataDTO("s2", 90, "v1.2"));
        expectedReportDataDTO.add(new ReportDataDTO("s2", 110, "v1.3"));

        when(reportMapper.loadAvg(serviceId, buildVersionIds)).thenReturn(expectedReportDataDTO);
        assertEquals(expectedReportDataDTO, reportMapper.loadAvg(serviceId, buildVersionIds));

        verify(reportMapper, times(1)).loadAvg(serviceId,buildVersionIds);
        verify(reportMapper, times(0)).loadMax(serviceId,buildVersionIds);

    }
}