package com.softserve.webtester.controller;

import com.softserve.webtester.dto.ResultCollectionFilter;
import com.softserve.webtester.dto.ResultFilter;
import com.softserve.webtester.model.ResultHistory;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ResultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/results/collections")
public class ResultHistoryCollectionController {

    @Autowired
    private ResultHistoryService resultHistoryService;

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(method = RequestMethod.GET)
    public String listResults(@ModelAttribute ResultCollectionFilter resultCollectionFilter, Model model) {

        model.addAttribute("buildVersions", metaDataService.loadAllBuildVersions());
        model.addAttribute("labels", metaDataService.loadAllLabels());
        model.addAttribute("list", resultHistoryService.loadAllCollections(resultCollectionFilter));
        return "collectionResult";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeCollectionResult(@PathVariable int id){

        resultHistoryService.deteleByCollectionId(id);
        System.out.println("after DELETIN "+id);
        return "redirect:/results/collections";
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCollectionRequests(@RequestBody int[] arr) {

        System.out.println("AAAAAAAAAA "+arr.length);
        resultHistoryService.deleteSelectedCollectionResults(arr);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showRequests(@PathVariable int id, @ModelAttribute ResultFilter resultFilter, Model model){

        model.addAttribute("list",resultHistoryService.loadAllRequestsByRunId(id));
        return "requestResult";
    }
}
