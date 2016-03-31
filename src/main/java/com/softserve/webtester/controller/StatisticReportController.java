package com.softserve.webtester.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.webtester.dto.StatisticFilterDTO;
import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.model.RequestCollection;
import com.softserve.webtester.service.ExcelReportGeneratorServise;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ReportService;

/**
 * Handles and retrieves statistic report page depending on the URI template. A user must be log-in first he
 * can access this page.
 */

@Controller
@RequestMapping(value = "/reports/statistic")
public class StatisticReportController {

    private static final String SERVICENAME = "serviceName";
    private static final String BUILDVERSIONS = "buildVersions";
    private static final String STATISTICBUILDVERSIONS = "statisticsBuildVersions";
    private static final String STATISTICS = "statistics";
    private static final String DATAFORMAT = "YYYY-MM-DD HH:mm:ss";
    private static final Logger LOGGER = Logger.getLogger(StatisticReportController.class);

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ExcelReportGeneratorServise excelReportGeneratorServise;

    /**
     * Retrieves page with statistic data report of {@link RequestCollection} running.
     * 
     * @param statisticFilterDTO DTO object using for filtering statistic data
     * @param result {@link BindingResult} instance
     * @param model container for statistic data
     * @return 'statistics' view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getStatistic(@Validated @ModelAttribute StatisticFilterDTO statisticFilterDTO, BindingResult result,
            Model model) {
        model.addAttribute(SERVICENAME, metaDataService.serviceLoadAllWithoutDeleted());
        List<BuildVersion> buildVersions = metaDataService.loadAllBuildVersions();
        statisticFilterDTO.setBuildVersions(buildVersions);
        model.addAttribute(BUILDVERSIONS, buildVersions);
        if (result.hasErrors()) {
            return "statistic/statistics";
        }
        if (ArrayUtils.isNotEmpty(statisticFilterDTO.getServiceId())){
        model.addAttribute(STATISTICBUILDVERSIONS, reportService.loadBuildVersionsName(statisticFilterDTO));
        model.addAttribute(STATISTICS, reportService.loadStatisticReportData(statisticFilterDTO));
        }
        return "statistic/statistics";
    }
    
    /**
     * Handles generating excel statistic report.
     * 
     * @param statisticFilterDTO DTO object using for filtering statistic data
     * @return if success, downloads the statistic report excel file;
     */
    @RequestMapping(value = "/xls", method = RequestMethod.GET)
    public void xlsP(HttpServletResponse response, @ModelAttribute StatisticFilterDTO statisticFilterDTO) {
        if (ArrayUtils.isNotEmpty(statisticFilterDTO.getServiceId())
            && ArrayUtils.isNotEmpty(statisticFilterDTO.getBuildVersionId())) {
            List<BuildVersion> buildVersions = metaDataService.loadAllBuildVersions();
            statisticFilterDTO.setBuildVersions(buildVersions);
            byte[] data = excelReportGeneratorServise.generateExcelReport(statisticFilterDTO);
            String fileName = new SimpleDateFormat(DATAFORMAT).format(new Date()) + ".xls";
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileName + "\""));
            response.setContentType("application/x-download");
            response.setContentLength(data.length);
            try(ServletOutputStream outputStream = response.getOutputStream()) {
                FileCopyUtils.copy(data, outputStream);               
            } catch (IOException e) {
                LOGGER.error("Unable to write data to response output stream", e);
            }
        }
    }
}