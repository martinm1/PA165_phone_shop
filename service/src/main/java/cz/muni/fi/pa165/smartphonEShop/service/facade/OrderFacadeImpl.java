package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Roman Nahalka
 * Class represents: Implementation of OrderFacade.
 */

@Service
@Transactional
public class OrderFacadeImpl implements OrderFacade
{

    @Override
    public OrderDTO findOrderById(Long id)
    {
        return null;
    }

    @Override
    public Collection<OrderDTO> findOrdersByOrderState(OrderState state)
    {
        return null;
    }

    @Override
    public Collection<OrderDTO> findOrdersByOrderDate(LocalDate orderDate)
    {
        return null;
    }

    @Override
    public Collection<OrderDTO> findOrdersByPerson(String personId)
    {
        return null;
    }

    @Override
    public Collection<OrderDTO> findOrdersByPhone(Long phoneId)
    {
        return null;
    }

    @Override
    public Collection<OrderDTO> getAllOrders()
    {
        return null;
    }

    @Override
    public void addClaim(Long orderId, Long claimId)
    {

    }

    @Override
    public void registerOrder(OrderDTO order)
    {

    }
}
