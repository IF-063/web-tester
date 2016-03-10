package com.softserve.webtester.controller;

import com.softserve.webtester.model.Label;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.validator.LabelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/configuration/labels")
public class LabelsController {

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private LabelValidator labelValidator;

    @InitBinder("label")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(labelValidator);
    }


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
        return "labels/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String confirmCreate(@Validated @ModelAttribute Label label, BindingResult result) {
        if (result.hasErrors()) {
            return "labels/create";
        }
        metaDataService.saveLabel(label);
        return "redirect:/configuration/labels";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteLabel(Label label) {
        metaDataService.deleteLabel(label.getId());
        return "redirect:/configuration/labels";
    }
}
