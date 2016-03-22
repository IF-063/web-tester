package com.softserve.webtester.controller;

import com.softserve.webtester.dto.GraphicData;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.ResultHistory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ResultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/reports/graphics")
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

        //model.addAttribute("list", resultHistoryService.loadResponseTime(graphicData));
        //return "graphicView";
        return "redirect:/reports/graphics";
    }
}
