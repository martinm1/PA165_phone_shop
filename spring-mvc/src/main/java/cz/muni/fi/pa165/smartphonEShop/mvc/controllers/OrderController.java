package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import cz.muni.fi.pa165.smartphonEShop.facade.PhoneFacade;
import lombok.Setter;
import cz.muni.fi.pa165.smartphonEShop.exceptions.EshopServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Stefan Holecko
 * Class represents: Order spring mvc controller.
 */

@Controller
@RequestMapping("/order")
@Setter
public class OrderController {

    final static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private PhoneFacade phoneFacade;

    @Autowired
    private PersonFacade personFacade;

    /**
     * Shows a list of all orders filtered by specified orderState
     * with the ability to add, delete or edit.
     *
     * @param filter selects which orders should be displayed
     * @param model  data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listBySate(@PathVariable String filter, Model model) {
        Collection<OrderDTO> orders;
        switch (filter) {
            case "created":
                orders = orderFacade.findOrdersByOrderState(OrderState.CREATED); break;
            case "finished":
                orders = orderFacade.findOrdersByOrderState(OrderState.FINISHED); break;
            case "canceled":
                orders = orderFacade.findOrdersByOrderState(OrderState.CANCELED); break;
            case "accepted":
                orders = orderFacade.findOrdersByOrderState(OrderState.ACCEPTED); break;
            case "outdated":
                orders = orderFacade.findOrdersByOrderState(OrderState.OUTDATED); break;
            default:
                orders = new ArrayList<>();
                model.addAttribute("alert_danger", "Unknown filter " + filter);
        }
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listAll(Model model) {
        Collection<OrderDTO> orders = orderFacade.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @RequestMapping(value = "/list/byPerson", method = RequestMethod.GET)
    public String listByPersonId(Model model, @RequestParam("personId") long personId) {
        Collection<OrderDTO> orders = orderFacade.findOrdersByPersonId(personId);
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @RequestMapping(value = "/list/byPhone", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listByPhoneId(Model model, @RequestParam("phoneId") long phoneId) {
        Collection<OrderDTO> orders = orderFacade.findOrdersByPhoneId(phoneId);
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @RequestMapping(value = "/list/byDate", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listByDate(Model model, @RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        Collection<OrderDTO> orders = orderFacade.findOrdersByOrderDate(date);
        model.addAttribute("orders", orders);
        return "order/list";
    }


    @ModelAttribute("claimSolution")
    public OrderState[] orderState() {
        return OrderState.values();
    }

    /**
     * Prepares an empty form for order.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new/{phoneId}/{userId}", method = RequestMethod.GET)
    public String newOrder(Model model, ModelMap map, @PathVariable("phoneId") long phoneId, @PathVariable("userId") long userId) {
        log.debug("new()");
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO();
        orderCreateDTO.setState(OrderState.CREATED);
        orderCreateDTO.setPerson(userId);
        orderCreateDTO.setPhone(phoneId);
        model.addAttribute("phone",phoneFacade.findPhoneById(phoneId));
        model.addAttribute("orderCreate", orderCreateDTO);
        model.addAttribute("personId", userId);
        return "order/new";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model) {
        log.debug("view order by Id");
        model.addAttribute("order", orderFacade.findOrderById(id));
        return "order/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("orderCreate") OrderCreateDTO formBean, BindingResult bindingResult,
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
            return "order/new";
        }
        //create product
        Long id = orderFacade.createOrder(formBean);

        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Order " + id + " was created");
        return "redirect:" + uriBuilder.path("/").toUriString();
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.POST)
    public String cancel(@PathVariable long id, Model model,UriComponentsBuilder uriBuilder,RedirectAttributes redirectAttributes) {
        try {
            orderFacade.cancelOrder(id);
            redirectAttributes.addFlashAttribute("alert_success", "Order number "+id+" was canceled.");
        } catch (EshopServiceException ex) {
            log.warn("cannot cancel order {}",id);
            redirectAttributes.addFlashAttribute("alert_danger", "Order number "+id+" was not canceled. "+ex.getMessage());
        }
        return "redirect:" + uriBuilder.path("/order/detail/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/finish/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String finish(@PathVariable long id, Model model,UriComponentsBuilder uriBuilder,RedirectAttributes redirectAttributes) {
        try {
            orderFacade.finishOrder(id);
            redirectAttributes.addFlashAttribute("alert_success", "Order number "+id+" was finished.");
        } catch (EshopServiceException ex) {
            log.warn("cannot finish order {}",id);
            redirectAttributes.addFlashAttribute("alert_danger", "Order number "+id+" was not finished. "+ex.getMessage());
        }
        return "redirect:" + uriBuilder.path("/order/detail/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/accept/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String accept(@PathVariable long id, Model model,UriComponentsBuilder uriBuilder,RedirectAttributes redirectAttributes) {
        try {
            orderFacade.acceptOrder(id);
            redirectAttributes.addFlashAttribute("alert_success", "Order number "+id+" was accepted.");
        } catch (EshopServiceException ex) {
            log.warn("cannot accept order {}", id);
            redirectAttributes.addFlashAttribute("alert_danger", "Order number "+id+" was not accepted. "+ex.getMessage());
        }
        return "redirect:" + uriBuilder.path("/order/detail/{id}").buildAndExpand(id).encode().toUriString();
    }

}
