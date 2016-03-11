package com.softserve.webtester.controller;

import com.softserve.webtester.model.ResultHistory;
import com.softserve.webtester.service.ResultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        System.out.println(list.size());
        model.addAttribute("list", list);
        return "collectionResult";
    }

    @RequestMapping("/remove/{id}")
    public String removeResult(@PathVariable int id){

        resultHistoryService.delete(id);
        return "redirect:/results/collections";
    }

    @RequestMapping(value = "/remove_selected", method = RequestMethod.POST)
    public String removeSelectedResults(@RequestParam List<Integer> ids){

        for(Integer id:ids) {
            resultHistoryService.delete(id);
        }
        System.out.println("Selected results deleted");
        return "redirect:/results/collections";
    }

    @RequestMapping(value = "/remove_all", method = RequestMethod.GET)
    public String removeAllResults(){

        resultHistoryService.deleteAll();
        System.out.println("All results deleted");
        return "redirect:/results/collections";
    }
}
