package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;
import cz.muni.fi.pa165.smartphonEShop.dto.PhoneCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PhoneDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.facade.PhoneFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Stefan Holecko
 * Class represents:
 */

public class PhoneController {

    final static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private PhoneFacade phoneFacade;

    /**
     * Shows a list of all phones and filtered by specified manufacturer,
     * with the ability to add, delete or edit.
     *
     * @param filter selects which phones should be displayed
     * @param model  data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    public String list(@PathVariable String filter, Model model) {
        Collection<PhoneDTO> phones;
        switch (filter) {
            case "all":
                phones = phoneFacade.getAllPhones();
                break;
            case "apple":
                phones = phoneFacade.findPhonesByManufacturer(Manufacturer.APPLE);
                break;
            case "huawei":
                phones = phoneFacade.findPhonesByManufacturer(Manufacturer.HUAWEI);
                break;
            case "htc":
                phones = phoneFacade.findPhonesByManufacturer(Manufacturer.HTC);
                break;
            case "samsung":
                phones = phoneFacade.findPhonesByManufacturer(Manufacturer.SAMSUNG);
                break;
            case "lg":
                phones = phoneFacade.findPhonesByManufacturer(Manufacturer.LG);
                break;
            default:
                phones = new ArrayList<>();
                model.addAttribute("alert_danger", "Unknown filter " + filter);
        }
        model.addAttribute("phones", phones);
        return "phone/list";
    }



    /**
     * Prepares an empty form for phone.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPhone(Model model) {
        log.debug("new()");
        model.addAttribute("phoneCreate", new PhoneCreateDTO());
        return "phone/new";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model) {
        log.debug("view phone by Id");
        model.addAttribute("phone", phoneFacade.findPhoneById(id));
        return "phone/view";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("categoryCreate") PhoneCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(formBean={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "phone/new";
        }
        //create product
        Long id = phoneFacade.createPhone(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Phone " + id + " was created");
        return "redirect:" + uriBuilder.path("/phone/list").toUriString();
    }


}
