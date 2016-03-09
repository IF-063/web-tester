package com.softserve.webtester.controller;

import com.softserve.webtester.model.Label;
import com.softserve.webtester.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/metadata/labels")
public class LabelsController {

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(method = RequestMethod.GET)
    public String getLabelsPage(Model model) {
        List<Label> labels = metaDataService.loadAllLabels();
        model.addAttribute("labels", labels);
        return "labels/labels";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreatePage(Model model) {
        Label label = new Label();
        model.addAttribute("label", label);
        return "labels/createLabel";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String confirmCreate(Label label) {
        metaDataService.saveLabel(label);
        return "redirect:/metadata/labels";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteLabel(Label label) {
        metaDataService.deleteLabel(label.getId());
        return "redirect:/metadata/labels";
    }
}
