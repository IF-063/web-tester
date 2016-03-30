package com.softserve.webtester.controller;

import com.softserve.webtester.dto.ResultCollectionFilterDTO;
import com.softserve.webtester.dto.ResultFilterDTO;
import com.softserve.webtester.model.ResultHistory;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ResultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String STATUS_COLLECION = "statusCollection";

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(method = RequestMethod.GET)
    public String listResults(@ModelAttribute ResultCollectionFilterDTO resultCollectionFilterDTO, Model model) {

        model.addAttribute("buildVersions", metaDataService.loadAllBuildVersions());
        model.addAttribute("labels", metaDataService.loadAllLabels());


        List<ResultHistory> list=resultHistoryService.loadAllCollections(resultCollectionFilterDTO);
        model.addAttribute("list", list);

        for (int i = 0; i < list.size(); i++) {
            model.addAttribute(STATUS_COLLECION+i, list.get(i).getStatus());
            System.out.println(STATUS_COLLECION+i+ "   "+ list.get(i).getStatus());
        }
        return "collectionResult";
    }


    @RequestMapping(value = "/run/{id}", method = RequestMethod.GET)
    public String listCollectionResultsByRuId(@ModelAttribute ResultCollectionFilterDTO resultCollectionFilterDTO,@PathVariable int id, Model model) {

        model.addAttribute("buildVersions", metaDataService.loadAllBuildVersions());
        model.addAttribute("labels", metaDataService.loadAllLabels());
        resultCollectionFilterDTO.setRunId(id);
        model.addAttribute("list", resultHistoryService.loadAllCollectionsByRunId(resultCollectionFilterDTO));
        return "requestResult";
    }

    @RequestMapping(value="/remove/{id}", method = RequestMethod.GET)
    public String removeResult(@PathVariable int id){

        resultHistoryService.deteleByCollectionId(id);
        return "redirect:/results/collections";
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequests(@RequestBody int[] arr) {

        resultHistoryService.deleteSelectedCollectionResults(arr);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showRequests(@PathVariable("id") int id,
                               @ModelAttribute ResultFilterDTO resultFilterDTO, Model model){

        model.addAttribute("list",resultHistoryService.loadAllRequestsByCollectionId(id));
        return "requestResult";
    }
}