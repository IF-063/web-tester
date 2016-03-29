package com.softserve.webtester.controller;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.softserve.webtester.dto.StatisticDataDTO;
import com.softserve.webtester.dto.StatisticFilterDTO;
import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ReportService;

@Controller
@RequestMapping(value = "/reports/statistic")
public class StatisticReportController {

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public String getStatistic(@ModelAttribute StatisticFilterDTO statisticFilterDTO, BindingResult result,
            Model model) {
        model.addAttribute("serviceName", metaDataService.serviceLoadAllWithoutDeleted());
        List<BuildVersion> buildVersions = metaDataService.loadAllBuildVersions();
        statisticFilterDTO.setBuildVersions(buildVersions);
        model.addAttribute("buildVersions", buildVersions);

        if (ArrayUtils.isNotEmpty(statisticFilterDTO.getServiceId())
                && ArrayUtils.isNotEmpty(statisticFilterDTO.getBuildVersionId())) {
            List<String> statisticsBuildVersions = reportService.loadBuildVersionsName(statisticFilterDTO);
            model.addAttribute("statisticsBuildVersions", statisticsBuildVersions);
            List<StatisticDataDTO> statistics = reportService.loadStatisticReportData(statisticFilterDTO);
            model.addAttribute("statistics", statistics);
        }

        // if (reportFilterDTO.getServiceId() != 0 && ArrayUtils.isNotEmpty(reportFilterDTO.getBuildVersionId())
        // && reportFilterDTO.getResponseTimeFilterMarker() != 0){
        // model.addAttribute("statistics", reportService.loadReportData(reportFilterDTO));
        // model.addAttribute("avarageResponseTime", reportService.loadAvarageResponseTimeForService(reportFilterDTO));
        // model.addAttribute("sla", metaDataService.serviceLoad(reportFilterDTO.getServiceId()).getSla());
        // }
        return "statistic/statistics";
    }
    
    @RequestMapping(value="/xls", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void xls() {
      System.out.println(1);
    }
}
