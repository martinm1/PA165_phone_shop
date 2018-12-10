package cz.muni.fi.pa165.smartphonEShop.rest.constrollers;

import cz.muni.fi.pa165.smartphonEShop.dto.OrderCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
import cz.muni.fi.pa165.smartphonEShop.rest.ApiUris;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.awt.*;
import java.util.Collection;

@RestController
@RequestMapping(ApiUris.ROOT_URI_ORDERS)
public class OrdersController {

    @Inject
    private OrderFacade orderFacade;

    /**
     * Get collection of orders.
     * @return OrderDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<OrderDTO> getOrders()
    {
        return orderFacade.getAllOrders();
    }

    /**
     * Get order by id.
     * @param id primary key for order.
     * @return OrderDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final OrderDTO getOrder(@PathVariable("id") long id)
    {
        OrderDTO orderDTO = orderFacade.findOrderById(id);

        if(orderDTO != null)
            return orderDTO;

        else
            throw new ResourceNotFoundException();
    }

    /**
     * Create a new order.
     * @param orderDTO with required field for creation.
     * @return created order.
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final OrderDTO createOrder(@RequestBody OrderCreateDTO orderDTO)
    {
        try
        {
            Long id = orderFacade.createOrder(orderDTO);
            return orderFacade.findOrderById(id);
        }

        catch (Exception ex)
        {
            throw new ResourceAlreadyExistingException();
        }
    }
}
