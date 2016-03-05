package com.softserve.webtester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.webtester.model.User;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.UserService;

@Controller
@RequestMapping("/metadata")
public class MetaDataController {

    @Autowired
    private MetaDataService metaDataService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public ModelAndView getMetadataPage() {
		ModelAndView modelAndView = new ModelAndView("metadata");
		return modelAndView;
    }
}