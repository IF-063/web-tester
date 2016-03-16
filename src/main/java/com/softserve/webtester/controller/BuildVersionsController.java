package com.softserve.webtester.controller;

import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.validator.BuildVersionValidator;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/configuration/buildVersions")
public class BuildVersionsController {

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private BuildVersionValidator buildVersionValidator;

    @InitBinder("buildVersion")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(buildVersionValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getBuildVersionsPage(Model model) {
        List<BuildVersion> buildVersions = metaDataService.loadAllBuildVersions();
        model.addAttribute("buildVersions", buildVersions);
        return "buildVersion/buildVersions";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreatePage(Model model) {
        model.addAttribute("pageTask", "Create");
        BuildVersion buildVersion = new BuildVersion();
        model.addAttribute("buildVersion", buildVersion);
        return "buildVersion/createOrModify";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String confirmCreate(@Validated @ModelAttribute BuildVersion buildVersion, BindingResult result) {
        if (result.hasErrors()) {
            return "buildVersion/createOrModify";
        }
        metaDataService.saveBuildVersion(buildVersion);
        return "redirect:/configuration/buildVersions";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modifyBuildVersion(@PathVariable int id, Model model) {
        model.addAttribute("pageTask", "Modify");
        BuildVersion buildVersion = metaDataService.loadBuildVersionById(id);
        model.addAttribute("buildVersion", buildVersion);
        return "buildVersion/createOrModify";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String confirmModify(@Validated @ModelAttribute BuildVersion buildVersion, BindingResult result) {
        if (result.hasErrors()) {
            return "buildVersion/createOrModify";
        }
        metaDataService.updateBuildVersion(buildVersion);
        return "redirect:/configuration/buildVersions";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteBuildVersion(@PathVariable int id) {
        metaDataService.deleteBuildVersion(id);
        return "redirect:/configuration/buildVersions";
    }

}
