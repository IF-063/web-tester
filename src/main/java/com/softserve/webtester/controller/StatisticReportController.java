package com.softserve.webtester.controller;

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
@RequestMapping(value="/reports/statistic")
public class StatisticReportController {
    
    @Autowired
    private MetaDataService metaDataService;
    
    @Autowired
    private ReportService reportService;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String getStatistic(@ModelAttribute ReportFilterDTO reportFilterDTO, Model model){
        model.addAttribute("serviceName", metaDataService.serviceLoadAll());
        model.addAttribute("buildVersions", metaDataService.loadAllBuildVersions());
        if (reportFilterDTO.getResponseTimeFilterMarker()==1){
            model.addAttribute("statisticData", reportService.loadWithAvarageResponseTime(reportFilterDTO));
        } else {
            model.addAttribute("statisticData", reportService.loadWithMaximumResponseTime(reportFilterDTO));
        }
        return "statistic/statistics";
    }

}
