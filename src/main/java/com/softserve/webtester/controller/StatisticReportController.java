package com.softserve.webtester.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.softserve.webtester.dto.StatisticFilterDTO;
import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ReportService;

@Controller
@RequestMapping(value = "/reports/statistic")
public class StatisticReportController {
    
    private static final String SERVICENAME = "serviceName";
    private static final String BUILDVERSIONS = "buildVersions";
    private static final String STATISTICBUILDVERSIONS = "statisticsBuildVersions";
    private static final String STATISTICS = "statistics";
    
    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public String getStatistic(@ModelAttribute StatisticFilterDTO statisticFilterDTO, BindingResult result,
            Model model) {
        model.addAttribute(SERVICENAME, metaDataService.serviceLoadAllWithoutDeleted());
        List<BuildVersion> buildVersions = metaDataService.loadAllBuildVersions();
        statisticFilterDTO.setBuildVersions(buildVersions);
        model.addAttribute(BUILDVERSIONS, buildVersions);
        if (ArrayUtils.isNotEmpty(statisticFilterDTO.getServiceId())
            && ArrayUtils.isNotEmpty(statisticFilterDTO.getBuildVersionId())) {
            model.addAttribute(STATISTICBUILDVERSIONS, reportService.loadBuildVersionsName(statisticFilterDTO));            
            model.addAttribute(STATISTICS, reportService.loadStatisticReportData(statisticFilterDTO));
        }
        return "statistic/statistics";
    }

    @RequestMapping(value = "/xls", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void xlsP(HttpServletResponse response, @ModelAttribute StatisticFilterDTO statisticFilterDTO) {
        List<BuildVersion> buildVersions = metaDataService.loadAllBuildVersions();
        statisticFilterDTO.setBuildVersions(buildVersions);
        if (ArrayUtils.isNotEmpty(statisticFilterDTO.getServiceId())
            && ArrayUtils.isNotEmpty(statisticFilterDTO.getBuildVersionId())) {
            reportService.GenerateExcelReport(statisticFilterDTO);
        }
        // File file= new File("2016.03.28.sql");
        // response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        // response.setContentType("application/x-download");
        // response.setContentLength((int)file.length());
        // InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        // FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
}
