package com.softserve.webtester.controller;

import com.softserve.webtester.model.ResponseTimeType;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ReportService;

@Controller
@RequestMapping(value = "/reports/graphics")
public class GraphicReportController {
    
    private static final String RESPONSETIMETYPE = "responseTimeType";

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public String getGraphic(@ModelAttribute ReportFilterDTO reportFilterDTO, Model model) {
        model.addAttribute("serviceName", metaDataService.serviceLoadAll());
        model.addAttribute("buildVersions", metaDataService.loadAllBuildVersions());
        model.addAttribute(RESPONSETIMETYPE, ResponseTimeType.values());

        if (reportFilterDTO.getServiceId() != 0 && ArrayUtils.isNotEmpty(reportFilterDTO.getBuildVersionId())) {
            model.addAttribute("graphicData", reportService.loadReportData(reportFilterDTO));
        }

        if(reportFilterDTO.getServiceId() != 0) {
            model.addAttribute("sla", metaDataService.serviceLoad(reportFilterDTO.getServiceId()).getSla());
        }
        return "graphic";
    }
}
