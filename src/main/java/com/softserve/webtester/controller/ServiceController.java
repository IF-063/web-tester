package com.softserve.webtester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.webtester.model.Service;
import com.softserve.webtester.service.MetaDataService;

/**
 * ServiceController class represents {@code Service} MVC Controller
 *
 * @author Roman Zolotar
 * @version 1.2
 */

@Controller
@RequestMapping(value = "/configuration/services")
public class ServiceController {

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(method = RequestMethod.GET)
    public String getServiceList(Model model) {
        List<Service> services = metaDataService.serviceLoadAll();
        model.addAttribute("services", services);
        return "service/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getService(@PathVariable(value = "id") Integer serviceId, Model model) {
        Service service = metaDataService.serviceLoad(serviceId);
        model.addAttribute("isUpdate", "true");
        model.addAttribute("service", service);
        return "service/update";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createService(Model model) {
        model.addAttribute("isUpdate", "false");
        model.addAttribute("service", new Service());
        return "service/update";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveCreatedService(@Validated @ModelAttribute("service") Service service, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isUpdate", "false");
            return "service/update";
        }
        metaDataService.serviceSave(service);
        return "redirect:/configuration/services";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String saveUpdatedService(@Validated @ModelAttribute("service") Service service, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isUpdate", "true");
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