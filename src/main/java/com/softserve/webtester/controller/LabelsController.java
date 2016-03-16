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
import org.springframework.web.bind.annotation.*;

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
        return "label/labels";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreatePage(Model model) {
        model.addAttribute("pageTask", "Create");
        Label label = new Label();
        model.addAttribute("label", label);
        return "label/createOrModify";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String confirmCreate(@Validated @ModelAttribute Label label, BindingResult result) {
        if (result.hasErrors()) {
            return "label/createOrModify";
        }
        metaDataService.saveLabel(label);
        return "redirect:/configuration/labels";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modifyLabel(@PathVariable int id, Model model) {
        model.addAttribute("pageTask", "Modify");
        Label label = metaDataService.loadLabelById(id);
        model.addAttribute("label", label);
        return "label/createOrModify";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String confirmModify(@Validated @ModelAttribute Label label, BindingResult result) {
        if (result.hasErrors()) {
            return "label/createOrModify";
        }
        metaDataService.updateLabel(label);
        return "redirect:/configuration/labels";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteLabel(@PathVariable int id) {
        metaDataService.deleteLabel(id);
        return "redirect:/configuration/labels";
    }
}
