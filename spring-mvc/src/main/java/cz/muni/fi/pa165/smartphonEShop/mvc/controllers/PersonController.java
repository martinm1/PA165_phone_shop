/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    /**
     * Shows a list of people with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("people", personFacade.getAllPeople());
        return "person/list";
    }

    /**
     * Prepares an empty form for person.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    /*
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPerson(Model model) {
        log.debug("new()");
        model.addAttribute("personCreate", new PersonCreateDTO());
        return "person/new";
    }*/
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("person", personFacade.findPersonById(id));
        return "person/view";
    }
}
