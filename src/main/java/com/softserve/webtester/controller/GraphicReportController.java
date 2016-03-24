package com.softserve.webtester.controller;

import com.softserve.webtester.dto.ReportFilterDTO;
import com.softserve.webtester.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ResultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


        if (reportFilterDTO.getResponseTimeFilterMarker()==1){
            model.addAttribute("statisticData", reportService.loadWithAvarageResponseTime(reportFilterDTO));
        }
        else {
            model.addAttribute("statisticData", reportService.loadWithMaximumResponseTime(reportFilterDTO));
        }


        /*Graphic graphic = resultHistoryService.loadAllGraphicData(graphicData);
        System.out.println("ServiceId: "+ graphic.getServiceId());
        System.out.println("Times: "+ graphic.getResponseTime().length);
        System.out.println("BuildVersionsIds: "+ graphic.getBuildVersionIds());
        System.out.println("SLA: "+ graphic.getSla());*/
        return "graphic";

    }



















    /*@RequestMapping(value="/showGraphic",method = RequestMethod.GET)
    public String showGraphic(@ModelAttribute GraphicData graphicData, Model model){

        //System.out.println("ServiceId: "+graphicData.getServiceName());
        //System.out.println("BuildVersionIds: "+graphicData.getBuildVersions().length);

        int[] times = resultHistoryService.loadResponseTime(graphicData);
        for(int t:times){
            System.out.println(t);
        }
        //int[] times={125,150,186};
        String[] buildVersionNames= resultHistoryService.loadBuildVersionNames(graphicData);
        for(String str:buildVersionNames){
            System.out.println(str);
        }

        model.addAttribute("times", times);
        model.addAttribute("buildVersionNames", buildVersionNames);
        //return "graphicView";
        return "redirect:/reports/graphics";
    }*/
}



/*
public class GraphicController {

    @Autowired
    private ResultHistoryService resultHistoryService;

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(method = RequestMethod.GET)
    public String graphicData(@ModelAttribute GraphicData graphicData, Model model) {

        model.addAttribute("buildVersions", metaDataService.loadAllBuildVersions());
        model.addAttribute("services", metaDataService.serviceLoadAll());
        return "graphic";
    }

    @RequestMapping(value="/showGraphic",method = RequestMethod.GET)
    public String showGraphic(@ModelAttribute GraphicData graphicData, Model model){

        System.out.println("ServiceId: "+graphicData.getServiceName());
        System.out.println("BuildVersionIds: "+graphicData.getBuildVersions().length);

        int[] times = resultHistoryService.loadResponseTime(graphicData);
        for(int t:times){
            System.out.println(t);
        }
        //int[] times={125,150,186};
        String[] buildVersionNames= resultHistoryService.loadBuildVersionNames(graphicData);
        for(String str:buildVersionNames){
            System.out.println(str);
        }

        model.addAttribute("times", times);
        model.addAttribute("buildVersionNames", buildVersionNames);
        //return "graphicView";
        return "redirect:/reports/graphics";
    }
}
 */
