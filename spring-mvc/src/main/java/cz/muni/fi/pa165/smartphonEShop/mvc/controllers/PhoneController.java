package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;
import cz.muni.fi.pa165.smartphonEShop.dto.PhoneCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.PhoneFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Stefan Holecko
 * Class represents:
 */

public class PhoneController {

    final static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private PhoneFacade phoneFacade;

    /**
     * Shows a list of phones with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("phones", phoneFacade.getAllPhones());
        return "phone/list";
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
        model.addAttribute("phoneCreate", new PhoneCreateDTO());
        return "phone/new";
    }
}
