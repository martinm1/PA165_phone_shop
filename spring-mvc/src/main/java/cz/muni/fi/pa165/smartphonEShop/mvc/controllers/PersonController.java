/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.PersonCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author martin
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    final static Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonFacade personFacade;
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model) {
        log.debug("view person by Id");
        model.addAttribute("order", personFacade.findPersonById(id));
        return "person/view";
    }
    
    @RequestMapping(value = "/list/byEmail", method = RequestMethod.GET)
    public String listByEmail(Model model, @RequestParam("email") String email) {
        PersonDTO person = personFacade.findPersonByEmail(email);
        model.addAttribute("personByEmail", person);
        return "person/list";
    }
    
    @RequestMapping(value = "/list/byPhoneNumber", method = RequestMethod.GET)
    public String listByPhoneNumber(Model model, @RequestParam("phoneNumber") String phoneNumber) {
        PersonDTO person = personFacade.findPersonByPhoneNumber(phoneNumber);
        model.addAttribute("personByPhoneNumber", person);
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
        model.addAttribute("peopleByPersonType", people);
        return "person/list";
    } 
    
    /**
     * Shows a list of people with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
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
        model.addAttribute("personCreate", new PersonCreateDTO());
        return "person/new";
    }
}
