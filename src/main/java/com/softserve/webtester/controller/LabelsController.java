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

/**
 * Handles and retrieves {@link Label} pages depending on the URI template. A user must be log-in
 * first he can access this page.
 */
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

    /**
     * Retrieves page with existing labels
     *
     * @param model {@link Model} object
     * @return view's name
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getLabelsPage(Model model) {
        List<Label> labels = metaDataService.loadAllLabels();
        model.addAttribute("labels", labels);
        return "label/labels";
    }

    /**
     * Retrieves page for creating new label with empty label instance
     *
     * @param model {@link Model} object
     * @return view's name
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreatePage(Model model) {
        model.addAttribute("pageTask", "Create");
        Label label = new Label();
        model.addAttribute("label", label);
        return "label/createOrModify";
    }

    /**
     * Handles creating new label
     *
     * @param label {@link Label} instance should be saved
     * @param result {@link BindingResult} validation handle object
     * @return if success, redirects to labels main page, in case of validation errors returns to creating page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String confirmCreate(@Validated @ModelAttribute Label label, BindingResult result) {
        if (result.hasErrors()) {
            return "label/createOrModify";
        }
        metaDataService.saveLabel(label);
        return "redirect:/configuration/labels";
    }

    /**
     * Retrieves label modify page
     *
     * @param id identifier of modifing {@link Label} instance
     * @param model {@link Model} object
     * @return view's name
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modifyLabel(@PathVariable int id, Model model) {
        model.addAttribute("pageTask", "Modify");
        Label label = metaDataService.loadLabelById(id);
        model.addAttribute("label", label);
        return "label/createOrModify";
    }

    /**
     * Handles label updating
     *
     * @param label {@link Label} instance should be updated
     * @param result {@link BindingResult} instance
     * @return if success, redirects to labels main page; in case of validation errors returns to modifing page
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String confirmModify(@Validated @ModelAttribute Label label, BindingResult result) {
        if (result.hasErrors()) {
            return "label/createOrModify";
        }
        metaDataService.updateLabel(label);
        return "redirect:/configuration/labels";
    }

    /**
     * Handles label deleting
     *
     * @param id identifier of {@link Label} should be deleted
     * @return redirects to labels main page
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteLabel(@PathVariable int id) {
        metaDataService.deleteLabel(id);
        return "redirect:/configuration/labels";
    }
}
