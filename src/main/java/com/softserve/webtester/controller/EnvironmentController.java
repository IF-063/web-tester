package com.softserve.webtester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.EnvironmentDbType;
import com.softserve.webtester.service.EnvironmentService;

@Controller
@RequestMapping("/environments")
public class EnvironmentController {

    @Autowired
    private EnvironmentService environmentService;

    @RequestMapping(method = RequestMethod.GET)
    public String getEnvironmentsPage(Model model) {
        List<Environment> environments = environmentService.loadAll();
        model.addAttribute("environmentList", environments);
        return "environment/environmentList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getEnvironmentCreatePage(Model model) {

        model.addAttribute("pageTask", "Create");
        Environment environment = new Environment();
        environment.setTimeMultiplier(environmentService.getDefaultTimeMultiplier());
        model.addAttribute("environment", environment);
        model.addAttribute("dbTypes", EnvironmentDbType.values());
        return "environment/environmentCreateOrUpdate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createEnvironment(Environment environment) {
        environmentService.save(environment);
        return "redirect:/environments";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEnvironmentUpdatePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("pageTask", "Update");
        model.addAttribute("id", ((Integer)id).toString());
        Environment environment = environmentService.load(id);
        model.addAttribute("environment", environment);
        model.addAttribute("dbTypes", EnvironmentDbType.values());
        return "environment/environmentCreateOrUpdate";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateEnvironment(Environment environment) {
        environmentService.update(environment);
        return "redirect:/environments";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteEnvironment(Environment environment) {
        environmentService.delete(environment);
        return "redirect:/environments";
    }

    @RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
    public String checkEnvironment(@PathVariable("id") int id, Model model) {
        Environment environment = environmentService.load(id);

        try {
            environmentService.checkConnection(environment);
            model.addAttribute("success",
                    " Environment: " + environment.getName() + ", connection was checked successfully");
        } catch (Exception e) {
            model.addAttribute("error", " Environment: " + environment.getName() + " error:" + e.getMessage());
        }
        return getEnvironmentsPage(model);
    }
}
