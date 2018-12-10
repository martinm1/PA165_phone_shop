package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

/**
 * Created by Stefan Holecko
 * Class represents:
 */

@Controller
@RequestMapping("/order")
@Setter
public class OrderController {

    final static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderFacade orderFacade;


    /**
     * Shows a list of orders with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("orders", orderFacade.getAllOrders());
        return "order/list";
    }

    /**
     * Prepares an empty form for order.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newOrder(Model model) {
        log.debug("new()");
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO();
        orderCreateDTO.setOrderDate(LocalDate.now());
        orderCreateDTO.setState(OrderState.CREATED);
        model.addAttribute("orderCreate", orderCreateDTO);
        return "order/new";
    }



}
