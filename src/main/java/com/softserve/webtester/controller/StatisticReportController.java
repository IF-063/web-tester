package com.softserve.webtester.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String getStatistic(@ModelAttribute StatisticFilterDTO statisticFilterDTO, Model model) {
        model.addAttribute("serviceName", metaDataService.serviceLoadAllWithoutDeleted());
        List<BuildVersion> buildVersions = metaDataService.loadAllBuildVersions();
        model.addAttribute("buildVersions", buildVersions);

        if (ArrayUtils.isNotEmpty(statisticFilterDTO.getServiceId())) {
            List<Integer> statisticsBuildVersionsId = 
                IntStream.of(statisticFilterDTO.getBuildVersionId()).boxed().collect(Collectors.toList());
            List<String> statisticsBuildVersions = 
                buildVersions.stream()
                             .filter(x -> statisticsBuildVersionsId.contains(x.getId()))
                             .map( x -> x.getName())
                             .collect(Collectors.toList());
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
}