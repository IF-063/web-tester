package com.softserve.webtester.controller;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.softserve.webtester.dto.RequestCollectionFilterDTO;
import com.softserve.webtester.model.RequestCollection;
import com.softserve.webtester.service.EnvironmentService;
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
    
    @Autowired
    private EnvironmentService environmentService; 

    @InitBinder("requestCollection")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(requestCollectionValidator);
        
    }

    /**
     * Retrieves page with all existing requestCollections.
     * 
     * @param requestCollectionFilterDTO DTO object using for filtering {@link RequestCollection} instances
     * @param model container for {@link RequestCollection} and {@link Label} instances
     * @return 'collections' view and founded requestCollections
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getAllRequestCollection(@ModelAttribute RequestCollectionFilterDTO requestCollectionFilterDTO, Model model) {
        model.addAttribute("labels", metaDataService.loadAllLabels());
        model.addAttribute("collectionList", requestCollectionService.loadAll(requestCollectionFilterDTO));
        model.addAttribute("environments", environmentService.loadAll());
        return "collection/collections";
    }

    /**
     * Retrieves page for creating new requestCollection with empty requestCollection instance.
     * 
     * @param model container for {@link RequestCollection}, {@link Request} and {@link Label} instances
     * @return 'collectionCreateEdit' view with requestCollection instance
     */
    @RequestMapping(value = "/newCollection", method = RequestMethod.GET)
    public String getEmptyFormForRequestCollection(Model model) {
        model.addAttribute("labels", metaDataService.loadAllLabels());
        model.addAttribute("requests", requestService.loadAll());        
        model.addAttribute("requestCollection", new RequestCollection());
        return "collection/collectionCreateEdit";
    }

    /**
     * Handles creating new request.
     * 
     * @param request {@link RequestCollection} instance should be saved
     * @param result {@link BindingResult} validation handle object
     * @param model container for {@link RequestCollection}, {@link Request} and {@link Label} instances
     * @return if success, redirects to requestCollections main page
     */
    @RequestMapping(value = "/newCollection", method = RequestMethod.POST)
    public String saveRequestCollection(@Validated @ModelAttribute RequestCollection requestCollection,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("labels", metaDataService.loadAllLabels());
            model.addAttribute("requests", requestService.loadAll());
            return "collection/collectionCreateEdit";
        }
        requestCollectionService.save(requestCollection);
        return "redirect:/tests/collections";
    }

    /**
     * Retrieves requestCollection edit page
     * 
     * @param id identifier of editing {@link RequestCollection} instance
     * @param model container for {@link RequestCollection}, {@link Request} and {@link Label} instances
     * @return 'collectionCreateEdit' view
     */ 
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getRequestCollection(@PathVariable int id, Model model) {
        model.addAttribute("labels", metaDataService.loadAllLabels());
        model.addAttribute("requests", requestService.loadAll());
        model.addAttribute("requestCollection", requestCollectionService.load(id));
        return "collection/collectionCreateEdit";
    }

    /**
     * Handles request updating.
     * 
     * @param id identifier of RequestCollection should be updated
     * @param requestCollection {@link RequestCollection} instance should be updated
     * @param result {@link BindingResult} instance
     * @param model container for {@link RequestCollection}, {@link Request} and {@link Label} instances
     * @return if success, redirects to requestCollections main page;
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String EditRequestCollection(@PathVariable int id,
            @Validated @ModelAttribute RequestCollection requestCollection, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("labels", metaDataService.loadAllLabels());
            model.addAttribute("requests", requestService.loadAll());
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
    
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public @ResponseBody int runRequestCollection(@RequestParam int environmentId,
            @RequestParam(value = "requestCollectionIdArray[]") int[] requestCollectionIdArray) {
        System.out.println("e: " + environmentId);
        System.out.println("collection: " + Arrays.toString(requestCollectionIdArray));
        System.out.println();
        return 1;
    }
}
