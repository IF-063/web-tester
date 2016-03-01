package com.softserve.webtester.controller;

import java.util.Arrays;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.ResponseType;
import com.softserve.webtester.model.VariableDataType;
import com.softserve.webtester.service.EnvironmentService;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.RequestService;
import com.softserve.webtester.validator.RequestNameUniqueValidator;

@Controller
@RequestMapping(value = "/tests/requests")
public class RequestController {
    // org.springframework.dao.DataIntegrityViolationException
    @Autowired
    private RequestService requestService;

    @Autowired
    private MetaDataService metaDataService;
    
    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private RequestNameUniqueValidator requestNameUniqueValidator;
    
//    @InitBinder("request")
//    public void initBinder(WebDataBinder binder) {
//	 binder.setValidator(requestNameUniqueValidator);
//    }
    @RequestMapping(value = "/e", method = RequestMethod.GET)
    public void testError(){
	throw new RuntimeException();
    }

    /**
     * Retrieves page with all existing requests. 
     * 
     * @return ModelAndView instance with 'requests' view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getRequestsPage() {
	ModelAndView modelAndView = new ModelAndView("request/requests");
	modelAndView.addObject("environments", environmentService.loadAll());
	modelAndView.addObject("requests", requestService.loadAll());
	return modelAndView;
    }

    /**
     * Handles deleting requests. If success, returns 204 (NO_CONTENT) http status.
     * 
     * @param requestIdArray array of requests identifiers should be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequests(@RequestBody int[] requestIdArray) {
	requestService.delete(requestIdArray);
    }

    /**
     * Creates ModelMap container with metadata lists. 
     * 
     * @return {@link ModelMap} instance
     */
    private ModelMap addMetaData() {
	ModelMap map = new ModelMap();
	map.addAttribute("applications", metaDataService.applicationLoadAll());
	map.addAttribute("services", metaDataService.serviceLoadAll());
	map.addAttribute("requestMethods", com.softserve.webtester.model.RequestMethod.values());
	map.addAttribute("responseTypes", ResponseType.values());
	map.addAttribute("variableDataTypes", VariableDataType.values());
	map.addAttribute("labels", metaDataService.loadAllLabels());
	return map;
    }

    /**
     * Retrieves create new request page.
     * 
     * @return ModelAndView instance with 'requestCreateEdit' view
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreateRequestPage(@RequestParam(value = "fromId", required = false) Integer fromId) {
	ModelAndView modelAndView = new ModelAndView("request/requestCreateEdit");
	modelAndView.addAllObjects(addMetaData());
	Request request = null;
	if (fromId != null) {
	    modelAndView.addObject("pageTitle", "Dublicate request");
	    request = requestService.createDuplicate(fromId);
	}
	else {
	    modelAndView.addObject("pageTitle", "Create request");
	    request = new Request();
	    request.setTimeout(requestService.getDefaultTimeout());
	}
	modelAndView.addObject("request", request);
	return modelAndView;
    }

    /**
     * Handles creating new request.
     * 
     * @param request {@link Request} instance should be saved
     * @param result {@link BindingResult} validation handle object 
     * @param map container with metadata lists
     * @return if success, redirects to requests main  page; in case of validation errors returns to creating page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String confirmNewRequest(@Validated @ModelAttribute Request request, BindingResult result, ModelMap map) {
	requestNameUniqueValidator.validate(request, result);
	if (result.hasErrors()) {
	    map.addAllAttributes(addMetaData());
	    return "request/requestCreateEdit";
	}
	System.out.println(request);
	System.out.println("h: " + request.getHeaders());
	System.out.println("l: " + request.getLabels());
	System.out.println("v: " + request.getVariables());
	System.out.println("db: " + request.getDbValidations());
	System.out.println("--------------------------------------------");
	requestService.save(request);
	return "redirect:/tests/requests";
    }

    /**
     * Retrieves request edit page.
     * 
     * @return ModelAndView instance with 'requestCreateEdit' view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getEditRequestPage(@PathVariable int id) {
	ModelAndView modelAndView = new ModelAndView("request/requestCreateEdit");
	modelAndView.addObject("pageTitle", "Edit request");
	modelAndView.addAllObjects(addMetaData());
	Request request = requestService.load(id);
	modelAndView.addObject("request", request);
	return modelAndView;
    }

    /**
     * Handles request updating.
     * 
     * @param id identifier of Request should be updated
     * @param request {@link Request} instance should be updated
     * @param result
     * @param map
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String confirmEditRequest(@PathVariable int id, @Validated @ModelAttribute Request request,
	    			     BindingResult result, ModelMap map) {
	if (result.hasErrors()) {
	    map.addAllAttributes(addMetaData());
	    return "request/requestCreateEdit";
	}
	System.out.println("editing:" + id);
	System.out.println(request.getVariables());
	requestService.update(request);
	return "redirect:/tests/requests";
    }

    /**
     * Handles request deleting. If success, returns 204 (NO_CONTENT) http status.
     * 
     * @param id identifier of {@link Request} should be updated
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
	requestService.delete(id);
    }

    // TODO Taras O. (if success - redirect to results page)
    /**
     * Handles requests run.
     * 
     * @param environmentId
     * @param requestIdArray
     * @return
     */
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody String runRequests(@RequestParam(value = "environmentId") int environmentId, 
	    @RequestParam(value = "requestIdArray[]") int[] requestIdArray) { 
	System.out.println("start at: " + new Date());
	System.out.println("e: " + environmentId);
	System.out.println("rqsts: " + Arrays.toString(requestIdArray));
	System.out.println();
	return "ok";
    }
    
    @RequestMapping(value = "/create/isRequestNameFree", method = RequestMethod.GET)
    public @ResponseBody String isRequestNameFree(@RequestParam("name") String name){
	return String.format("{\"valid\": %b}", !"".equals(name) && requestService.isRequestNameFree(name));
    }
}