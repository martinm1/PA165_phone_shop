package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.StockCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.StockFacade;
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

/**
 *
 * @author martin
 */
@Controller
@RequestMapping("/stock")
public class StockController {
    
    final static Logger log = LoggerFactory.getLogger(StockController.class);

    @Autowired
    @Setter
    private StockFacade stockFacade;

    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model) {
        log.debug("view stock by Id");
        model.addAttribute("stock", stockFacade.findStockById(id));
        return "stock/view";
    }
    
    /**
     * Shows a list of stocks with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public String listAll(Model model) {
        model.addAttribute("stocks", stockFacade.getAllStocks());
        return "stock/list";
    }
    
    @RequestMapping(value = "/list/byAddress", method = RequestMethod.GET)
    public String listByAddressId(Model model, @RequestParam("addressId") long addressId) {
        StockDTO stock = stockFacade.findStockByAddressId(addressId);
        model.addAttribute("stock", stock);
        return "stock/list";
    }
    
    @RequestMapping(value = "/list/byName", method = RequestMethod.GET)
    public String listByName(Model model, @RequestParam("name") String name) {
        StockDTO stock = stockFacade.findStockByName(name);
        model.addAttribute("stock", stock);
        return "stock/list";
    }

    /**
     * Prepares an empty form for stock.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String newStock(Model model) {
        log.debug("new()");
        model.addAttribute("stockCreate", new StockCreateDTO());
        return "stock/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String create(@Valid @ModelAttribute("stockCreate") StockCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(formBean={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError : {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "phone/new";
        }
        //create product
        Long id = stockFacade.createStock(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Stock " + id + " was created");
        return "redirect:" + uriBuilder.path("/stock/list/all").toUriString();
    }
}
