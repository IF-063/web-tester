package com.softserve.webtester.controller;

import com.softserve.webtester.dto.ReportDataDTO;
import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.dto.ReportFinalData;
import com.softserve.webtester.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ResultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/reports/graphics")
public class GraphicReportController {

    @Autowired
    private ResultHistoryService resultHistoryService;

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public String getGraphic(@ModelAttribute ReportFilterDTO reportFilterDTO, Model model) {

        model.addAttribute("serviceName", metaDataService.serviceLoadAll());
        model.addAttribute("buildVersions", metaDataService.loadAllBuildVersions());

        ReportFinalData reportFinalData = reportService.loadGraphicData(reportFilterDTO);
        int[] times =  reportFinalData.getResponseTimes();

        //List<ReportDataDTO> list = reportService.loadResponseTime(reportFilterDTO);
        //model.addAttribute(reportService.loadResponseTime(reportFilterDTO));

        return "graphic";
    }
}