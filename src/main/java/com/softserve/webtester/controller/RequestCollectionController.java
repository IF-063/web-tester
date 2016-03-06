package com.softserve.webtester.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.RequestCollection;
import com.softserve.webtester.model.ResponseType;
import com.softserve.webtester.model.VariableDataType;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.RequestCollectionService;
import com.softserve.webtester.service.RequestService;

@Controller
@RequestMapping(value = "/tests/collections")
public class RequestCollectionController {
    
    @Autowired
    private RequestCollectionService requestCollectionService;
    
    @Autowired
    private RequestService requestService;
    
    @Autowired
    private MetaDataService metaDataService;
    
    private ModelMap addLabels() {
	ModelMap map = new ModelMap();
	map.addAttribute("labels", metaDataService.loadAllLabels());
	return map;
    }

    private ModelMap addRequests() {
    ModelMap map = new ModelMap();
    map.addAttribute("requests", requestService.loadAll(null, null, null, null));
    return map;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllRequestCollection(){
	ModelAndView model = new ModelAndView("collection/collections");
	model.addObject("pageTitle", "Collections");
	model.addObject("ñollectionsList", requestCollectionService.loadAll());
	return model;
	}
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getRequestCollectionById(@PathVariable int id){
	ModelAndView model = new ModelAndView("collection/collection");
	model.addObject("collection", requestCollectionService.load(id));
	return model;
    }

    @RequestMapping(value = "/newCollection", method = RequestMethod.GET)
    public ModelAndView getEmptyFormForRequestCollection(){
	ModelAndView model = new ModelAndView("collection/createCollection");
	model.addAllObjects(addLabels());
	model.addAllObjects(addRequests());
	model.addObject("pageTitle", "Create New Request Collection");
	model.addObject("requestCollection", new RequestCollection());
	return model;
    }
    
    @RequestMapping(value = "/newCollection", method = RequestMethod.POST)
    public String saveRequestCollection(@ModelAttribute RequestCollection requestCollection, BindingResult result, ModelMap map){
	if (result.hasErrors()) {
	    map.addAllAttributes(addLabels());
	    map.addAllAttributes(addRequests());
	    return "collection/createCollection";
	}
	requestCollectionService.save(requestCollection);
	return "redirect:/tests/collections";
    }
}
