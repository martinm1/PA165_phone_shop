package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.StockCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.StockFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author martin
 */
public class StockController {
    
    final static Logger log = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockFacade stockFacade;

    /**
     * Shows a list of stocks with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("stocks", stockFacade.getAllStocks());
        return "stock/list";
    }

    /**
     * Prepares an empty form for order.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newStock(Model model) {
        log.debug("new()");
        model.addAttribute("stockCreate", new StockCreateDTO());
        return "stock/new";
    }
}
