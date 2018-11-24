package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.*;
import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.smartphonEShop.service.service.OrderService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Roman Nahalka
 * Class represents: Tests for OrderFacade.
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderFacadeTest extends AbstractTestNGSpringContextTests
{
    @Mock
    private OrderService orderService;

    @Mock
    private BeanMappingService bms;

    @Autowired
    @InjectMocks
    private OrderFacade orderFacade;

    private Order order1;
    private Order order2;

    private OrderDTO orderDTO1;
    private OrderDTO orderDTO2;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void testPrepare()
    {
        order1 = new Order();
        order2 = new Order();

        order1.setId(10L);
        order2.setId(20L);

        order1.setState(OrderState.CREATED);
        order2.setState(OrderState.FINISHED);

        orderDTO1 = new OrderDTO();
        orderDTO2 = new OrderDTO();
    }

    @Test
    public void getAllOrders()
    {
        List<Order> retOrders = Arrays.asList(order1, order2);
        List<OrderDTO> retDTOs = Arrays.asList(orderDTO1, orderDTO2);
        when(orderService.getAllOrders()).thenReturn(retOrders);
        when(bms.mapTo(orderService.getAllOrders(), OrderDTO.class)).thenReturn(retDTOs);

        Collection<OrderDTO> orders = orderFacade.getAllOrders();

        Assert.assertEquals(2, orders.size());
    }
}
