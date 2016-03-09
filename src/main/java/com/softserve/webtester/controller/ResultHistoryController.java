package com.softserve.webtester.controller;

import com.softserve.webtester.model.ResultHistory;
import com.softserve.webtester.service.ResultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ResultHistoryController {

    private ResultHistoryService resultHistoryService;

    //@Autowired and @Qualifier annotations are used for injecting ResultHistoryService
    @Autowired(required=true)
    @Qualifier(value="resultHistoryService")
    public void setResultHistoryService(ResultHistoryService resultHistoryService){
        this.resultHistoryService = resultHistoryService;
    }

    @ModelAttribute("list")
    public List<ResultHistory> listResults() {
        // Delegate to service
        return resultHistoryService.loadAll();
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public String listResults(Model model) {

        model.addAttribute("result", new ResultHistory());
        List<ResultHistory> list = resultHistoryService.loadAll();
        model.addAttribute("list", list);
        model.addAttribute("list_size", list.size());
        return "result";
    }

    @RequestMapping("/remove/{id}")
    public String removeResult(@PathVariable("id") int id){

        resultHistoryService.delete(id);
        return "redirect:/results";
    }

    @RequestMapping(value = "/remove_selected", method = RequestMethod.POST)
    public String removeSelectedResults(HttpServletRequest request){

        String[] ids = request.getParameterValues("id");
        for(String str:ids) {
            int i = Integer.parseInt(str);
            System.out.println(i);
            resultHistoryService.delete(i);
        }
        System.out.println("Selected results deleted");
        return "redirect:/results";
    }

    @RequestMapping(value = "/remove_all", method = RequestMethod.GET)
    public String removeAllResults(){

        resultHistoryService.deleteAll();
        System.out.println("All results deleted");
        return "redirect:/results";
    }

    @RequestMapping("/show/{id}")
    public String showResult(@PathVariable("id") int id, Model model){


        System.out.println("BEFORE LOAD_BY_ID");
        ResultHistory result = resultHistoryService.loadById(id);
        System.out.println("AFTER LOAD_BY_ID");
        model.addAttribute("result",result);
        return "result_detailed";
    }
}
