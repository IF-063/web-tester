package com.softserve.webtester.controller;

import com.softserve.webtester.dto.ResultFilterDTO;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.ResultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/results/requests")
public class ResultHistoryController {

    @Autowired
    private ResultHistoryService resultHistoryService;

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(method = RequestMethod.GET)
    public String listResults(@ModelAttribute ResultFilterDTO resultFilterDTO, Model model) {

        model.addAttribute("applications", metaDataService.applicationLoadAll());
        model.addAttribute("services", metaDataService.serviceLoadAll());
        model.addAttribute("list", resultHistoryService.loadAll(resultFilterDTO));
        return "requestResult";
    }

    @RequestMapping(value = "/run/{id}", method = RequestMethod.GET)
    public String listResultsByRuId(@ModelAttribute ResultFilterDTO resultFilterDTO, int id, Model model) {

        model.addAttribute("applications", metaDataService.applicationLoadAll());
        model.addAttribute("services", metaDataService.serviceLoadAll());
        resultFilterDTO.setRunId(id);
        model.addAttribute("list", resultHistoryService.loadAllRequestsByRunId(resultFilterDTO));
        return "requestResult";
    }

    @RequestMapping("/remove/{id}")
    public String removeResult(@PathVariable int id){

        resultHistoryService.delete(id);
        return "redirect:/results/requests";
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequests(@RequestBody int[] arr) {

        resultHistoryService.deleteSelectedResults(arr);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String seeResult(@PathVariable int id, Model model){

        model.addAttribute("result",resultHistoryService.loadById(id));
        return "result_detailed";
    }
}