package com.softserve.webtester.controller;

import com.softserve.webtester.model.Application;
import com.softserve.webtester.model.ResultHistory;
import com.softserve.webtester.service.ResultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/results/requests")
public class ResultHistoryController {

    @Autowired
    private ResultHistoryService resultHistoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String listResults(Model model) {
        List<ResultHistory> list = resultHistoryService.loadAll();
        // list.forEach(System.out::println);
        System.out.print(list.isEmpty());
        model.addAttribute("list", list);
        return "requestResult";
    }

    @RequestMapping("/remove/{id}")
    public String removeResult(@PathVariable int id){

        resultHistoryService.delete(id);
        return "redirect:/results/requests";
    }

    @RequestMapping(value = "/remove_selected", method = RequestMethod.POST)
    public String removeSelectedResults(@RequestParam List<Integer> ids){

        for(Integer id:ids) {
            resultHistoryService.delete(id);
        }
        System.out.println("Selected results deleted");
        return "redirect:/results/requests";
    }

    @RequestMapping(value = "/remove_all", method = RequestMethod.GET)
    public String removeAllResults(){

        resultHistoryService.deleteAll();
        System.out.println("All results deleted");
        return "redirect:/results/requests";
    }

    @RequestMapping("/{id}")
    public String showResult(@PathVariable("id") int id, Model model){

        ResultHistory result = resultHistoryService.loadById(id);
        model.addAttribute("result",result);
        return "result_detailed";
    }
}


