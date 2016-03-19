package com.softserve.webtester.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/reports/statistic")
public class Statistic {
    
    @RequestMapping(method = RequestMethod.GET)
    public String getAllStatistic(){
        return "statistic/statistics";
    }

}
