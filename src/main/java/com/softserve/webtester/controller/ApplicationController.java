package com.softserve.webtester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.webtester.model.Application;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.validator.ApplicationValidator;

/**
 * ApplicationController class represents {@code Application} MVC Controller
 *
 * @author Roman Zolotar
 * @version 1.3
 */

@Controller
@RequestMapping(value = "configuration/applications")
public class ApplicationController {

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private ApplicationValidator applicationValidator;

    @InitBinder("application")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(applicationValidator);
    }

    /*
     * @RequestMapping(method = RequestMethod.GET) public String
     * getApplicationList(Model model) { List<Application> applications =
     * metaDataService.applicationLoadAll(); model.addAttribute("applications",
     * applications); return "application/list"; }
     */

    @RequestMapping(method = RequestMethod.GET)
    public String getNotDeletedApplicationList(Model model) {
        List<Application> applications = metaDataService.applicationLoadAllWithoutDeleted();
        model.addAttribute("applications", applications);
        return "application/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getApplication(@PathVariable(value = "id") Integer applicationId, Model model) {
        Application application = metaDataService.applicationLoad(applicationId);
        model.addAttribute("isUpdate", true);
        model.addAttribute("application", application);
        return "application/update";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createApplication(Model model) {
        model.addAttribute("isUpdate", false);
        model.addAttribute("application", new Application());
        return "application/update";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveCreatedApplication(@Validated @ModelAttribute("application") Application application,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isUpdate", false);
            return "application/update";
        }
        metaDataService.applicationSave(application);
        return "redirect:/configuration/applications";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String saveUpdatedApplication(@Validated @ModelAttribute("application") Application application,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isUpdate", true);
            return "application/update";
        }
        metaDataService.applicationUpdate(application);
        return "redirect:/configuration/applications";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteApplication(@PathVariable(value = "id") Integer id, Model model) {
        metaDataService.applicationSoftDelete(id);
        return "redirect:/configuration/applications";
    }

}