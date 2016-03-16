package com.softserve.webtester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.EnvironmentDbType;
import com.softserve.webtester.service.EnvironmentService;
import com.softserve.webtester.validator.EnvironmentValidator;

@Controller
@RequestMapping("/configuration/environments")
public class EnvironmentController {

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private EnvironmentValidator environmentValidator;

    @InitBinder("environment")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(environmentValidator);
    }

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
    public String createEnvironment(@Validated @ModelAttribute Environment environment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTask", "Create");
            model.addAttribute("dbTypes", EnvironmentDbType.values());
            return "environment/environmentCreateOrUpdate";
        }
        environmentService.save(environment);
        return "redirect:/configuration/environments";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEnvironmentUpdatePage(@PathVariable int id, Model model) {
        model.addAttribute("pageTask", "Update");
        model.addAttribute("id", ((Integer) id).toString());
        Environment environment = environmentService.load(id);
        model.addAttribute("environment", environment);
        model.addAttribute("dbTypes", EnvironmentDbType.values());
        return "environment/environmentCreateOrUpdate";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateEnvironment(@Validated @ModelAttribute Environment environment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTask", "Update");
            model.addAttribute("dbTypes", EnvironmentDbType.values());
            return "environment/environmentCreateOrUpdate";
        }
        environmentService.update(environment);
        return "redirect:/configuration/environments";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteEnvironment(Environment environment) {
        environmentService.delete(environment);
        return "redirect:/configuration/environments";
    }

    @RequestMapping(value = "/check/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> checkEnvironment(@PathVariable int id, Model model) {
        String message = "";
        HttpStatus status = HttpStatus.OK;
        
        Environment environment = environmentService.load(id);

        try {
            environmentService.checkConnection(environment);
            message = " Environment: " + environment.getName() + ", connection was checked successfully";
        } catch (Exception e) {
            message = " Environment: " + environment.getName() + " check finished with  error:" + e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(message, status);
        
        return responseEntity;
    }
}
