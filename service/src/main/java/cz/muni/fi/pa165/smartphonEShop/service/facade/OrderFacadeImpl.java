package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.OrderCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.OrderService;
import cz.muni.fi.pa165.smartphonEShop.service.service.PersonService;
import cz.muni.fi.pa165.smartphonEShop.service.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Created by Roman Nahalka
 * Class represents: Implementation of OrderFacade.
 * @author xnahalka
 */

@Service
@Transactional
public class OrderFacadeImpl implements OrderFacade
{
    @Autowired
    private OrderService orderService;

    @Autowired
    private PersonService personService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private BeanMappingService bms;

    @Override
    public OrderDTO findOrderById(Long id)
    {
        Order order = orderService.findOrderById(id);

        if(order == null)
            return null;

        else
            return bms.mapTo(order, OrderDTO.class);
    }

    @Override
    public Collection<OrderDTO> findOrdersByOrderState(OrderState state)
    {
        List<Order> orders = orderService.findOrdersByOrderState(state);

        return bms.mapTo(orders, OrderDTO.class);
    }

    @Override
    public Collection<OrderDTO> findOrdersByOrderDate(LocalDate orderDate)
    {
        List<Order> orders = orderService.findOrdersByOrderDate(orderDate);

        return bms.mapTo(orders, OrderDTO.class);
    }

    @Override
    public Collection<OrderDTO> findOrdersByPersonId(Long personId)
    {
        List<Order> orders = orderService.findOrdersByPersonId(personId);

        return bms.mapTo(orders, OrderDTO.class);
    }

    @Override
    public Collection<OrderDTO> findOrdersByPhoneId(Long phoneId)
    {
        List<Order> orders = orderService.findOrdersByPhoneId(phoneId);

        return bms.mapTo(orders, OrderDTO.class);
    }

    @Override
    public Collection<OrderDTO> getAllOrders()
    {
        List<Order> orders = orderService.getAllOrders();

        return bms.mapTo(orders, OrderDTO.class);
    }

    @Override
    public void addClaim(Long orderId, Long claimId) {
        orderService.addClaim(orderId, claimId);
    }

    @Override
    public void removeClaim(Long orderId, Long claimId)
    {
        orderService.removeClaim(orderId, claimId);
    }

    @Override
    public Long createOrder(OrderCreateDTO order)
    {
        Order mappedOrder = new Order();

        mappedOrder.setOrderDate(order.getOrderDate());
        mappedOrder.setPerson(personService.findPersonById(order.getPerson().getId()));
        mappedOrder.setPhone(phoneService.findPhoneById(order.getPhone().getId()));
        mappedOrder.setState(OrderState.CREATED);

        return orderService.createOrder(bms.mapTo(order, Order.class));
    }

    @Override
    public void cancelOrder(Long id) {
        orderService.cancelOrder(orderService.findOrderById(id));
    }
}
