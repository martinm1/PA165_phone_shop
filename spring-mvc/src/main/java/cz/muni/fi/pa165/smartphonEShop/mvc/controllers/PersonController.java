/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PersonAuthDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PersonCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import java.util.ArrayList;
import java.util.Collection;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 *
 * @author martin
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    final static Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    @Setter
    private PersonFacade personFacade;
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model) {
        log.debug("view person by Id");
        model.addAttribute("person", personFacade.findPersonById(id));
        return "person/view";
    }
    
    @RequestMapping(value = "/list/byEmail", method = RequestMethod.GET)
    public String listByEmail(Model model, @RequestParam("email") String email) {
        PersonDTO person = personFacade.findPersonByEmail(email);
        model.addAttribute("person", person);
        return "person/list";
    }
    
    @RequestMapping(value = "/list/byPhoneNumber", method = RequestMethod.GET)
    public String listByPhoneNumber(Model model, @RequestParam("phoneNumber") String phoneNumber) {
        PersonDTO person = personFacade.findPersonByPhoneNumber(phoneNumber);
        model.addAttribute("person", person);
        return "person/list";
    }
    
    @RequestMapping(value = "/list/byPersonType", method = RequestMethod.GET)
    public String listByPersonType(@PathVariable String filter, Model model) {
        Collection<PersonDTO>  people;// = personFacade.getPeopleByPersonType(personType);
        
        switch (filter) {
            case "unsigned user":
                people = personFacade.getPeopleByPersonType(PersonType.UNSIGNED_USER); break;
            case "signed user":
                people = personFacade.getPeopleByPersonType(PersonType.SIGNED_USER); break;
            case "admin":
                people = personFacade.getPeopleByPersonType(PersonType.ADMIN); break;
            case "employee":
                people = personFacade.getPeopleByPersonType(PersonType.EMPLOYEE); break;
            default:
                people = new ArrayList<>();
                model.addAttribute("alert_danger", "Unknown filter " + filter);
        }
        model.addAttribute("people", people);
        return "person/list";
    } 
    
    /**
     * Shows a list of people with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public String listAll(Model model) {
        model.addAttribute("people", personFacade.getAllPeople());
        return "person/list";
    }

    /**
     * Prepares an empty form for person.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPerson(Model model) {
        log.debug("new()");
//        model.addAttribute("addressCreate", new AddressCreateDTO());
        model.addAttribute("personCreate", new PersonCreateDTO());
        return "person/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("personCreate") PersonCreateDTO person, BindingResult bindingResult,
                               Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder)
    {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "person/new";
        }
        //create product
        personFacade.registerPerson(person, person.getPassword());
                //report success
        redirectAttributes.addFlashAttribute("alert_success", "Person " /*+ id*/ + " was created");
        return "redirect:" + uriBuilder.path("/person/list").toUriString();
    }


    @ModelAttribute("genders")
    public Gender[] genders() {
        return Gender.values();
    }

    @RequestMapping(value = "/newAuth", method = RequestMethod.GET)
    public String newAuth(Model model)
    {
        model.addAttribute("login", new PersonAuthDTO());

        return "person/auth";
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public String auth(@Valid @ModelAttribute("login") PersonAuthDTO person, BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder)
    {
        if(bindingResult.hasErrors())
        {
            for(FieldError fe : bindingResult.getFieldErrors())
            {
                model.addAttribute(fe.getField() + "_error", true);
            }

            return "person/auth";
        }

        if(personFacade.auth(person))
        {
            Long id = personFacade.findPersonByEmail(person.getEmail()).getId();

            return "redirect:" + uriBuilder.path("/person/view/" + id).toUriString();
        }

        else
        {
            model.addAttribute("msg", "Wrong email or password");

            return "person/auth";
        }

    }
}
