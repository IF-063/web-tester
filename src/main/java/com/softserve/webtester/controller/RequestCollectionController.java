package com.softserve.webtester.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.RequestCollection;
import com.softserve.webtester.model.ResponseType;
import com.softserve.webtester.model.VariableDataType;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.RequestCollectionService;
import com.softserve.webtester.service.RequestService;

/**
 * Handles and retrieves {@link RequestCollection} pages depending on the URI template. A user must be log-in first he 
 * can access this page.
 * 
 * @author Yura Lubinec
 */
@Controller
@RequestMapping(value = "/tests/collections")
public class RequestCollectionController {
    
    @Autowired
    private RequestCollectionService requestCollectionService;
    
    @Autowired
    private RequestService requestService;
    
    @Autowired
    private MetaDataService metaDataService;
        
    /**
     * Retrieves page with all existing requestCollections.     
     * @return ModelAndView instance with 'requestCollections' view and founded requestCollections
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllRequestCollection(){
		ModelAndView model = new ModelAndView("collection/collections");
		model.addObject("pageTitle", "Collections");
		model.addObject("collectionList", requestCollectionService.loadAll());
		return model;
	}
    
    /**
     * Retrieves requestCollection edit page.
     * 
     * @param id identifier of editing {@link RequestCollection} instance
     * 
     * @return ModelAndView instance with 'collectionEdit' view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getRequestCollection(@PathVariable int id){
		ModelAndView model = new ModelAndView("collection/collectionCreateEdit");
		model.addAllObjects(addLabels());
		model.addAllObjects(addRequests());
		model.addObject("pageTitle", "Edit RequestCollection");
		model.addObject("requestCollection", requestCollectionService.load(id));
		return model;
    }
    
    /**
     * Handles request updating.
     * 
     * @param id identifier of RequestCollection should be updated
     * @param requestCollection {@link RequestCollection} instance should be updated
     * @param result {@link BindingResult} instance
     * @param map {@link ModelMap} instance
     * @return if success, redirects to requestCollections main  page;
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String EditRequestCollection(@PathVariable int id, @ModelAttribute RequestCollection requestCollection ,
		     BindingResult result, ModelMap map) {
    if (result.hasErrors()) {
        map.addAllAttributes(addLabels());
        map.addAllAttributes(addRequests());
        return "collection/collectionCreateEdit";
    }
		requestCollectionService.update(requestCollection);
		return "redirect:/tests/collections";
    }
    
    /**
     * Retrieves page for creating new requestCollection with empty requestCollection instance    
     * @return ModelAndView instance with 'collectionCreate' view with request instance
     */
    @RequestMapping(value = "/newCollection", method = RequestMethod.GET)
    public ModelAndView getEmptyFormForRequestCollection(){
		ModelAndView model = new ModelAndView("collection/collectionCreateEdit");
		model.addAllObjects(addLabels());
		model.addAllObjects(addRequests());
		model.addObject("pageTitle", "Create New Request Collection");
		model.addObject("requestCollection", new RequestCollection());
		return model;
    }
    
    /**
     * Handles creating new request.
     * 
     * @param request {@link RequestCollection} instance should be saved
     * @param result {@link BindingResult} validation handle object 
     * @param map container with metadata lists
     * @return if success, redirects to requestCollections main  page
     */
    @RequestMapping(value = "/newCollection", method = RequestMethod.POST)
    public String saveRequestCollection(@ModelAttribute RequestCollection requestCollection, BindingResult result, ModelMap map){
		if (result.hasErrors()) {
		    map.addAllAttributes(addLabels());
		    map.addAllAttributes(addRequests());
		    return "collection/collectionCreateEdit";
		}
		requestCollectionService.save(requestCollection);
		return "redirect:/tests/collections";
    }
    
    /**
     * Handles requestCollection deleting. If success, returns 204 (NO_CONTENT) HTTP status.
     * 
     * @param id identifier of {@link RequestCollection} should be updated
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
	    requestCollectionService.delete(id);
    }
    
    /**
     * Handles deleting requestCollections. If success, returns 204 (NO_CONTENT) HTTP status.
     * 
     * @param requestColelctionIdArray array of requests identifiers should be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void detele(@RequestBody int[] requestCollectionIdArray) {
    	requestCollectionService.delete(requestCollectionIdArray);
    }
    
    /**
     * Creates ModelMap container with labels lists. 
     * 
     * @return {@link ModelMap} instance
     */
    private ModelMap addLabels() {
  		ModelMap map = new ModelMap();
  		map.addAttribute("labels", metaDataService.loadAllLabels());
  		return map;
    }

    /**
     * Creates ModelMap container with requests lists. 
     * 
     * @return {@link ModelMap} instance
     */
    private ModelMap addRequests() {
  	    ModelMap map = new ModelMap();
  	    map.addAttribute("requests", requestService.loadAll(null, null, null, null));
  	    return map;
    }
}
