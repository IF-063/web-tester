package com.softserve.webtester.controller;

import com.softserve.webtester.dto.ReportDataDTO;
import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.model.ResponseTimeType;
import com.softserve.webtester.model.Service;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ReportService;
import com.softserve.webtester.service.ResultHistoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 *  Unit testing of GraphicReportController class with mock objects
 */
@RunWith(MockitoJUnitRunner.class)
public class GraphicReportControllerTest {

    @Mock
    MetaDataService metaDataService;

    @Mock
    ResultHistoryService resultHistoryService;

    @Mock
    ReportService reportService;

    @InjectMocks
    private GraphicReportController graphicReportController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Using a mock MetaDataService, mock ResultHistoryService and mock ReportService objects for
     * testing getGraphic() method that consists of setting expectations on the mock objects with
     * assertEquals statement and doing the verification of the method calls made to a mock objects with verify statement
     */
    @Test
    public void getGraphicTest() {

        List<Service> expectedServices = new ArrayList<>();
        expectedServices.add(new Service(1, "name_1", "description_1", false, 500));
        expectedServices.add(new Service(2, "name_2", "description_2", false, 750));
        when(metaDataService.serviceLoadAll()).thenReturn(expectedServices);
        assertEquals(expectedServices, metaDataService.serviceLoadAll());
        verify(metaDataService, times(1)).serviceLoadAll();

        List<BuildVersion> expectedBuildVersions = new ArrayList<>();
        expectedBuildVersions.add(new BuildVersion(10, "name_10", "description_10", false));
        expectedBuildVersions.add(new BuildVersion(20, "name_20", "description_20", false));
        when(metaDataService.loadAllBuildVersions()).thenReturn(expectedBuildVersions);
        assertEquals(expectedBuildVersions, metaDataService.loadAllBuildVersions());
        verify(metaDataService, times(1)).loadAllBuildVersions();

        int serviceId = 2;
        int[] buildVersionIds = {1, 4};
        ResponseTimeType responseTimeFilterMarker = ResponseTimeType.MAX;
        ReportFilterDTO reportFilterDTO = new ReportFilterDTO(serviceId, buildVersionIds, responseTimeFilterMarker);

        Model model = new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        List<ReportDataDTO> expectedReportDataDTO = new ArrayList<>();
        expectedReportDataDTO.add(new ReportDataDTO("s2", 70, "v1.0"));
        expectedReportDataDTO.add(new ReportDataDTO("s2", 90, "v1.1"));
        when(reportService.loadReportData(reportFilterDTO)).thenReturn(expectedReportDataDTO);
        assertEquals(expectedReportDataDTO, reportService.loadReportData(reportFilterDTO));
        verify(reportService, times(1)).loadReportData(reportFilterDTO);

        Service service = new Service(2, "name_1", "description_1", false, 500);

        when(metaDataService.serviceLoad(reportFilterDTO.getServiceId())).thenReturn(service);
        assertEquals(service, metaDataService.serviceLoad(reportFilterDTO.getServiceId()));
        verify(metaDataService, times(1)).serviceLoad(reportFilterDTO.getServiceId());

        String viewName = graphicReportController.getGraphic(reportFilterDTO, model);
        assertEquals("graphic", viewName);
    }
}