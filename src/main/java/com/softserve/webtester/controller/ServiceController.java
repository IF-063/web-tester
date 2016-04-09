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

import com.softserve.webtester.model.Service;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.validator.ServiceValidator;

/**
 * ServiceController class represents {@code Service} MVC Controller
 *
 * @author Roman Zolotar
 * @version 1.3
 */

@Controller
@RequestMapping(value = "/configuration/services")
public class ServiceController {
    private static final String SERVICE = "service";
    private static final String SERVICES = "services";
    private static final String IS_UPDATE = "isUpdate";

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private ServiceValidator serviceValidator;

    @InitBinder("service")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(serviceValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getNotDeletedServiceList(Model model) {
        List<Service> services = metaDataService.serviceLoadAllWithoutDeleted();
        model.addAttribute(SERVICES, services);
        return "service/list";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String getService(@PathVariable(value = "id") Integer serviceId, Model model) {
        Service service = metaDataService.serviceLoad(serviceId);
        model.addAttribute(IS_UPDATE, "true");
        model.addAttribute(SERVICE, service);
        return "service/update";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createService(Model model) {
        model.addAttribute(IS_UPDATE, "false");
        model.addAttribute(SERVICE, new Service());
        return "service/update";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveCreatedService(@Validated @ModelAttribute("service") Service service, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute(IS_UPDATE, "false");
            return "service/update";
        }
        metaDataService.serviceSave(service);
        return "redirect:/configuration/services";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String saveUpdatedService(@Validated @ModelAttribute("service") Service service, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute(IS_UPDATE, "true");
            return "service/update";
        }
        metaDataService.serviceUpdate(service);
        return "redirect:/configuration/services";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteService(@PathVariable(value = "id") Integer id, Model model) {
        metaDataService.serviceSoftDelete(id);
        return "redirect:/configuration/services";
    }
}