package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressCreateDTO;

import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;
import cz.muni.fi.pa165.smartphonEShop.facade.AddressFacade;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;

@Controller
@RequestMapping("/address")
public class AddressController {

    final static Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    @Setter
    private AddressFacade addressFacade;

    @RequestMapping(value = "/list/by", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listBy(Model model,
                         @RequestParam(value = "country", required = false) String country,
                         @RequestParam(value = "city", required = false)String city,
                         @RequestParam(value = "street", required = false) String street,
                         @RequestParam(value = "number", required = false) String number) {

        HashMap<AddressEnum,String> specificator = new HashMap<>();
        if(country != null)
            specificator.put(AddressEnum.COUNTRY, country);
        if(city != null)
            specificator.put(AddressEnum.CITY, city);
        if(street != null)
            specificator.put(AddressEnum.STREET_NAME, street);
        if(number != null)
            specificator.put(AddressEnum.STREET_NUMBER, number);

        if(specificator.size() == 0) {
            model.addAttribute("addresses", addressFacade.getAllAddresses());
            return "address/list";
        } else {
            model.addAttribute("addresses", addressFacade.findAllAddressesBy(specificator));
            return "address/list";
        }

    }


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAddress(Model model)
    {
        AddressCreateDTO addressCreateDTO = new AddressCreateDTO();
        model.addAttribute("addressCreate", addressCreateDTO);
        return "address/new";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model) {
        log.debug("view address by Id");
        model.addAttribute("address", addressFacade.findAddressById(id));
        return "address/view";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("addressCreate") AddressCreateDTO formBean, BindingResult bindingResult,
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
            return "address/new";
        }
        //create product
        Long id = addressFacade.createAddress(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Address " + id + " was created");
        return "redirect:" + uriBuilder.path("/address/list").toUriString();
    }


}
