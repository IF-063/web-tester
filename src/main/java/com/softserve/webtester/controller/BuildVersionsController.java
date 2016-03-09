package com.softserve.webtester.controller;

import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.service.MetaDataService;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/metadata/build_versions")
public class BuildVersionsController {

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(method = RequestMethod.GET)
    public String getBuildVersionsPage(Model model) {
        List<BuildVersion> buildVersions = metaDataService.loadAllBuildVersions();
        model.addAttribute("build_versions", buildVersions);
        return "build_version/build_versions";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreatePage(Model model) {
        BuildVersion buildVersion = new BuildVersion();
        model.addAttribute("build_version", buildVersion);
        return "build_version/createOrModify";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String confirmCreate(BuildVersion buildVersion) {
        metaDataService.saveBuildVersion(buildVersion);
        return "redirect:/metadata/build_versions";
    }

    @RequestMapping(value = "/{id}/modify", method = RequestMethod.GET)
    public String modifyBuildVersion(@PathVariable("id")int id, Model model) {
        BuildVersion buildVersion = metaDataService.loadBuildVersionById(id);
        model.addAttribute("build_version", buildVersion);
        return "build_version/createOrModify";
    }

    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public String confirmModify(BuildVersion buildVersion) {
        metaDataService.updateBuildVersion(buildVersion);
        return "redirect:/metadata/build_versions";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteBuildVersion(BuildVersion buildVersion) {
        metaDataService.deleteBuildVersion(buildVersion.getId());
        return "redirect:/metadata/build_versions";
    }

}
