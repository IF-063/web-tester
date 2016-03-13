package com.softserve.webtester.controller;

import com.softserve.webtester.model.ResultHistory;
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

    @RequestMapping(method = RequestMethod.GET)
    public String listResults(Model model) {

        model.addAttribute("result", new ResultHistory());
        List<ResultHistory> list = resultHistoryService.loadAllCollections();

        model.addAttribute("list", list);
        return "collectionResult";
    }

    @RequestMapping("/remove/{id}")
    public String removeResult(@PathVariable int id){

        resultHistoryService.deteleByCollectionId(id);
        return "redirect:/results/collections";
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequests(@RequestBody int[] arr) {

        resultHistoryService.deleteSelectedCollectionResults(arr);
    }

    @RequestMapping(value = "/remove_all", method = RequestMethod.GET)
    public String removeAllCollectionResults(){

        resultHistoryService.deleteAllCollectionResults();
        System.out.println("All collection results deleted");
        return "redirect:/results/requests";
    }

    @RequestMapping("/{id}")
    public String showRequests(@PathVariable("id") int id, Model model){

        System.out.println("SEE RESULT_ID "+id);
        List<ResultHistory> list = resultHistoryService.loadAllRequestsByRunId(id);
        model.addAttribute("list",list);
        return "requestResult";
    }
}
