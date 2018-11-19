package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
import cz.muni.fi.pa165.smartphonEShop.service.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    @Autowired
    private BeanMappingService bms;

    @Override
    public OrderDTO findOrderById(Long id)
    {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public Collection<OrderDTO> findOrdersByOrderState(OrderState state)
    {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public Collection<OrderDTO> findOrdersByOrderDate(LocalDate orderDate)
    {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public Collection<OrderDTO> findOrdersByPerson(String personId)
    {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public Collection<OrderDTO> findOrdersByPhone(Long phoneId)
    {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public Collection<OrderDTO> getAllOrders()
    {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public void addClaim(Long orderId, Long claimId)
    {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public void registerOrder(OrderDTO order)
    {
        //TODO
        throw new NotImplementedException();
    }
}
