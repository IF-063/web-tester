package com.softserve.webtester.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.model.ResponseTimeType;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ReportService;

@Controller
@RequestMapping(value = "/reports/graphics")
public class GraphicReportController {

    private static final String SERVICE_NAME = "serviceName";
    private static final String BUILD_VERSIONS = "buildVersions";
    private static final String RESPONSETIMETYPE = "responseTimeType"; // TODO VS: Rename
    private static final String GRAPHICDATA = "graphicData"; // TODO VS: Rename
    private static final String SLA = "sla";

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private ReportService reportService;

    /**
     * Retrieves page with graphic data report.
     * @param reportFilterDTO DTO object is using for filtering statistic data
     * @param model {@link Model} object
     * @return 'graphic' view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getGraphic(@ModelAttribute ReportFilterDTO reportFilterDTO, Model model) {
        model.addAttribute(SERVICE_NAME, metaDataService.serviceLoadAll());
        model.addAttribute(BUILD_VERSIONS, metaDataService.loadAllBuildVersions());
        model.addAttribute(RESPONSETIMETYPE, ResponseTimeType.values());

        if (ArrayUtils.isNotEmpty(reportFilterDTO.getBuildVersionId())) {
            model.addAttribute(GRAPHICDATA, reportService.loadReportData(reportFilterDTO));
        }

        model.addAttribute(SLA, metaDataService.serviceLoad(reportFilterDTO.getServiceId()).getSla());
        return "graphic";
    }
}