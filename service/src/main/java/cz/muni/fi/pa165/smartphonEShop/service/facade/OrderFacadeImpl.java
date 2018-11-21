package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
import cz.muni.fi.pa165.smartphonEShop.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Created by Roman Nahalka
 * Class represents: Implementation of OrderFacade.
 */

@Service
@Transactional
public class OrderFacadeImpl implements OrderFacade
{
    @Autowired
    private OrderService orderService;

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
    public Collection<OrderDTO> findOrdersByPerson(String personId)
    {
        List<Order> orders = orderService.findOrdersByPerson(personId);

        return bms.mapTo(orders, OrderDTO.class);
    }

    @Override
    public Collection<OrderDTO> findOrdersByPhone(Long phoneId)
    {
        List<Order> orders = orderService.findOrdersByPhone(phoneId);

        return bms.mapTo(orders, OrderDTO.class);
    }

    @Override
    public Collection<OrderDTO> getAllOrders()
    {
        List<Order> orders = orderService.getAllOrders();

        return bms.mapTo(orders, OrderDTO.class);
    }

    @Override
    public void addClaim(Long orderId, Long claimId)
    {
        orderService.addClaim(orderId, claimId);
    }

    @Override
    public void removeClaim(Long orderId, Long claimId)
    {
        orderService.removeClaim(orderId, claimId);
    }

    @Override
    public Long createOrder(OrderDTO order)
    {
        return orderService.createOrder(bms.mapTo(order, Order.class));
    }
}
