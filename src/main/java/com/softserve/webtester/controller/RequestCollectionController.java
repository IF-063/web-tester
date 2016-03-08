package com.softserve.webtester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import com.softserve.webtester.dto.RequestCollectionFilterDTO;
import com.softserve.webtester.model.RequestCollection;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.RequestCollectionService;
import com.softserve.webtester.service.RequestService;
import com.softserve.webtester.validator.CollectionValidator;

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
    
    @Autowired
    private CollectionValidator requestCollectionValidator;
    
    @InitBinder("requestCollection")
    public void initBinder(WebDataBinder binder) {
	binder.addValidators(requestCollectionValidator);
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
  	    map.addAttribute("requests", requestService.loadAll());
  	    return map;
    }
    
    /**
     * Retrieves page with all existing requestCollections
     * @param requestCollectionFilterDTO DTO object using for filtering {@link RequestCollection} instances     
     * @return ModelAndView instance with 'requestCollections' view and founded requestCollections
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllRequestCollection(@ModelAttribute RequestCollectionFilterDTO requestCollectionFilterDTO){
		ModelAndView model = new ModelAndView("collection/collections");
		model.addObject("pageTitle", "Collections");
		model.addObject("labels", metaDataService.loadAllLabels());
		model.addObject("collectionList", requestCollectionService.loadAll(requestCollectionFilterDTO));
		return model;
	}
    
    /**
     * Retrieves page for creating new requestCollection with empty requestCollection or with duplicate of existing request 
     * instance. 
     * @param fromId identifier of existing {@link RequestCollection}   
     * @return ModelAndView instance with 'collectionCreate' view with requestCollection instance
     */
    @RequestMapping(value = "/newCollection", method = RequestMethod.GET)
    public ModelAndView getEmptyFormForRequestCollection(@RequestParam(value = "fromId", required = false) Integer id){
		ModelAndView model = new ModelAndView("collection/collectionCreateEdit");
		model.addAllObjects(addLabels());
		model.addAllObjects(addRequests());
		RequestCollection requestCollection = null;
		if (id != null) {
			model.addObject("pageTitle", "Duplicate Request Collection");
		    requestCollection = requestCollectionService.createDuplicate(id);
		}
		else {
			model.addObject("pageTitle", "Create New Request Collection");
			requestCollection = new RequestCollection();
		}		
		model.addObject("requestCollection", requestCollection);
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
    public String saveRequestCollection(@Validated @ModelAttribute RequestCollection requestCollection, BindingResult result, ModelMap map){
		if (result.hasErrors()) {
		    map.addAllAttributes(addLabels());
		    map.addAllAttributes(addRequests());
		    return "collection/collectionCreateEdit";
		}
		requestCollectionService.save(requestCollection);
		return "redirect:/tests/collections";
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
    public String EditRequestCollection(@PathVariable int id,@Validated @ModelAttribute RequestCollection requestCollection,
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
}
