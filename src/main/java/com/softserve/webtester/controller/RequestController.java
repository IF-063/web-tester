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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.webtester.dto.RequestFilterDTO;
import com.softserve.webtester.editor.ApplicationEditor;
import com.softserve.webtester.editor.ServiceEditor;
import com.softserve.webtester.model.Application;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.ResponseType;
import com.softserve.webtester.model.Service;
import com.softserve.webtester.model.VariableDataType;
import com.softserve.webtester.service.EnvironmentService;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.RequestService;
import com.softserve.webtester.validator.RequestValidator;

/**
 * Handles and retrieves {@link Request} pages depending on the URI template. A user must be log-in first he can access
 * this page.
 * 
 * @author Taras Oglabyak
 */
@Controller
@RequestMapping(value = "/tests/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private ApplicationEditor applicationEditor;

    @Autowired
    private ServiceEditor serviceEditor;

    @Autowired
    private RequestValidator requestValidator;

    @InitBinder("request")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Application.class, applicationEditor);
        binder.registerCustomEditor(Service.class, serviceEditor);
        binder.addValidators(requestValidator);
    }

    /**
     * Retrieves page with existing requests.
     * 
     * @param requestFilterDTO DTO object using for filtering {@link Request} instances.
     * @return ModelAndView instance with 'requests' view and filtered requests
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getRequestsPage(@ModelAttribute RequestFilterDTO requestFilterDTO) {
        ModelAndView modelAndView = new ModelAndView("request/requests");
        modelAndView.addObject("applications", metaDataService.applicationLoadAll());
        modelAndView.addObject("services", metaDataService.serviceLoadAll());
        modelAndView.addObject("labels", metaDataService.loadAllLabels());
        modelAndView.addObject("environments", environmentService.loadAll());
        modelAndView.addObject("requests", requestService.loadAll(requestFilterDTO));
        return modelAndView;
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
     * Retrieves page for creating new request with empty request instance or with duplicate of existing request
     * instance.
     * 
     * @param fromId identifier of existing {@link Request}
     * @return ModelAndView instance with 'requestCreateEdit' view with request instance
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreateRequestPage(@RequestParam(value = "fromId", required = false) Integer fromId) {
        ModelAndView modelAndView = new ModelAndView("request/requestCreateEdit");
        modelAndView.addAllObjects(addMetaData());
        Request request = null;
        if (fromId != null) {
            modelAndView.addObject("pageTitle", "Duplicate request");
            request = requestService.createDuplicate(fromId);
        } else {
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
     * @return if success, redirects to requests main page; in case of validation errors returns to creating page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String confirmNewRequest(@Validated @ModelAttribute Request request, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.addAllAttributes(addMetaData());
            return "request/requestCreateEdit";
        }
        System.out.println(request);
        requestService.save(request);
        return "redirect:/tests/requests";
    }

    /**
     * Retrieves request edit page.
     * 
     * @param id identifier of editing {@link Request} instance
     * @return ModelAndView instance with 'requestCreateEdit' view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getEditRequestPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("request/requestCreateEdit");
        modelAndView.addObject("pageTitle", "Edit request");
        modelAndView.addAllObjects(addMetaData());
        modelAndView.addObject("request", requestService.load(id));
        return modelAndView;
    }

    /**
     * Handles request updating.
     * 
     * @param id identifier of Request should be updated
     * @param request {@link Request} instance should be updated
     * @param result {@link BindingResult} instance
     * @param map {@link ModelMap} instance
     * @return if success, redirects to requests main page; in case of validation errors returns to editing page
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String confirmEditRequest(@PathVariable int id, @Validated @ModelAttribute Request request,
            BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.addAllAttributes(addMetaData());
            return "request/requestCreateEdit";
        }
        System.out.println("editing:" + id);
        System.out.println(request);
        requestService.update(request);
        return "redirect:/tests/requests";
    }

    /**
     * Handles request deleting. If success, returns 204 (NO_CONTENT) HTTP status.
     * 
     * @param id identifier of {@link Request} should be updated
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        requestService.delete(id);
    }

    /**
     * Handles deleting requests. If success, returns 204 (NO_CONTENT) HTTP status.
     * 
     * @param requestIdArray array of requests identifiers should be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequests(@RequestBody int[] requestIdArray) {
        requestService.delete(requestIdArray);
    }

    // TODO Taras O. redirect to request run results
    /**
     * Handles requests run.
     * 
     * @param environmentId identifier of {@link Environment} instance
     * @param requestIdArray identifiers of {@link Request} instance
     * @return identifier of requests run
     */
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public @ResponseBody int runRequests(@RequestParam(value = "environmentId") int environmentId,
            @RequestParam(value = "requestIdArray[]") int[] requestIdArray) {
        System.out.println("start at: " + new Date());
        System.out.println("e: " + environmentId);
        System.out.println("rqsts: " + Arrays.toString(requestIdArray));
        System.out.println();
        return 1;
    }

    /**
     * Checks the unique of {@link Request} instance's name.
     * 
     * @param name name property should be checked
     * @param exclusionId identifier of {@link Request} instance should be excluded from checking
     * @return JSON object with <code>valid</code> name and boolean value
     */
    @RequestMapping(value = "/create/isRequestNameFree", method = RequestMethod.GET)
    public @ResponseBody String isRequestNameFree(@RequestParam("name") String name,
            @RequestParam(value = "exclusionId") int exclusionId) {
        return String.format("{\"valid\": %b}",
                !"".equals(name) && requestService.isRequestNameFree(name, exclusionId));
    }
}