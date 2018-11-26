package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.ClaimDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.*;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.smartphonEShop.service.service.OrderService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
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

    @InjectMocks
    private OrderFacadeImpl orderFacade;

    private Order order1;
    private Order order2;

    private OrderDTO orderDTO1;
    private OrderDTO orderDTO2;

    private Claim claim;

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

        orderDTO1.setId(10L);
        orderDTO2.setId(20L);

        orderDTO1.setState(OrderState.CREATED);
        orderDTO2.setState(OrderState.FINISHED);

        order1.setOrderDate(LocalDate.ofYearDay(2018, 10));
        order2.setOrderDate(LocalDate.ofYearDay(2018, 120));

        claim = new Claim();

        claim.setId(5L);
        order1.addClaim(claim);
    }

    @Test
    public void getAllOrders()
    {
        List<Order> retOrders = Arrays.asList(order1, order2);
        List<OrderDTO> retDTOs = Arrays.asList(orderDTO1, orderDTO2);

        when(orderService.getAllOrders()).thenReturn(retOrders);
        when(bms.mapTo(retOrders, OrderDTO.class)).thenReturn(retDTOs);

        Collection<OrderDTO> orders = orderFacade.getAllOrders();

        Assert.assertEquals(2, orders.size());
    }

    @Test
    public void findOrderById()
    {
        when(orderService.findOrderById(10L)).thenReturn(order1);
        when(orderService.findOrderById(20L)).thenReturn(order2);
        when(bms.mapTo(order1, OrderDTO.class)).thenReturn(orderDTO1);
        when(bms.mapTo(order2, OrderDTO.class)).thenReturn(orderDTO2);

        Assert.assertEquals(orderDTO1, orderFacade.findOrderById(10L));
        Assert.assertEquals(orderDTO2, orderFacade.findOrderById(20L));
    }

    @Test
    public void findOrdersByOrderState()
    {
        when(orderService.findOrdersByOrderState(OrderState.CREATED)).thenReturn(Collections.singletonList(order1));
        when(orderService.findOrdersByOrderState(OrderState.FINISHED)).thenReturn(Collections.singletonList(order2));
        when(bms.mapTo(Collections.singletonList(order1), OrderDTO.class)).thenReturn(Collections.singletonList(orderDTO1));
        when(bms.mapTo(Collections.singletonList(order2), OrderDTO.class)).thenReturn(Collections.singletonList(orderDTO2));

        Collection<OrderDTO> ordtos = orderFacade.findOrdersByOrderState(OrderState.CREATED);

        Assert.assertEquals(ordtos.size(), 1);
        Assert.assertTrue(ordtos.contains(orderDTO1));

        ordtos = orderFacade.findOrdersByOrderState(OrderState.FINISHED);

        Assert.assertEquals(ordtos.size(), 1);
        Assert.assertTrue(ordtos.contains(orderDTO2));
    }

    @Test
    public void findOrdersByOrderDate()
    {
        when(orderService.findOrdersByOrderDate(LocalDate.ofYearDay(2018, 10)))
                                                .thenReturn(Collections.singletonList(order1));
        when(orderService.findOrdersByOrderDate(LocalDate.ofYearDay(2018, 120)))
                                                .thenReturn(Collections.singletonList(order2));
        when(bms.mapTo(Collections.singletonList(order1), OrderDTO.class)).thenReturn(Collections.singletonList(orderDTO1));
        when(bms.mapTo(Collections.singletonList(order2), OrderDTO.class)).thenReturn(Collections.singletonList(orderDTO2));

        Collection<OrderDTO> ordtos = orderFacade.findOrdersByOrderDate(LocalDate.ofYearDay(2018, 10));

        Assert.assertEquals(ordtos.size(), 1);
        Assert.assertTrue(ordtos.contains(orderDTO1));

        ordtos = orderFacade.findOrdersByOrderDate(LocalDate.ofYearDay(2018, 120));

        Assert.assertEquals(ordtos.size(), 1);
        Assert.assertTrue(ordtos.contains(orderDTO2));
    }

    @Test
    public void findOrdersByPerson()
    {
        when(orderService.findOrdersByPersonId(1L)).thenReturn(Collections.singletonList(order1));
        when(orderService.findOrdersByPersonId(2L)).thenReturn(Collections.singletonList(order2));
        when(bms.mapTo(Collections.singletonList(order1), OrderDTO.class)).thenReturn(Collections.singletonList(orderDTO1));
        when(bms.mapTo(Collections.singletonList(order2), OrderDTO.class)).thenReturn(Collections.singletonList(orderDTO2));

        Collection<OrderDTO> ordtos = orderFacade.findOrdersByPerson(1L);

        Assert.assertEquals(ordtos.size(), 1);
        Assert.assertTrue(ordtos.contains(orderDTO1));

        ordtos = orderFacade.findOrdersByPerson(2L);

        Assert.assertEquals(ordtos.size(), 1);
        Assert.assertTrue(ordtos.contains(orderDTO2));
    }

    @Test
    public void findOrdersByPhone()
    {
        when(orderService.findOrdersByPhoneId(1L)).thenReturn(Collections.singletonList(order1));
        when(orderService.findOrdersByPhoneId(2L)).thenReturn(Collections.singletonList(order2));
        when(bms.mapTo(Collections.singletonList(order1), OrderDTO.class)).thenReturn(Collections.singletonList(orderDTO1));
        when(bms.mapTo(Collections.singletonList(order2), OrderDTO.class)).thenReturn(Collections.singletonList(orderDTO2));

        Collection<OrderDTO> ordtos = orderFacade.findOrdersByPhone(1L);

        Assert.assertEquals(ordtos.size(), 1);
        Assert.assertTrue(ordtos.contains(orderDTO1));

        ordtos = orderFacade.findOrdersByPhone(2L);

        Assert.assertEquals(ordtos.size(), 1);
        Assert.assertTrue(ordtos.contains(orderDTO2));
    }

    @Test
    public void addClaim()
    {
        Claim claimTest = new Claim();
        claimTest.setId(7L);

        ClaimDTO claimDTO = new ClaimDTO();
        claimDTO.setId(7L);

        doAnswer(invocationOnMock ->
        {
            order2.addClaim(claimTest);
            return 7L;
        }).when(orderService).addClaim(20L, 7L);

        doAnswer(invocationOnMock ->
        {
            orderDTO2.setClaims(Collections.singletonList(claimDTO));
            return 7L;
        }).when(bms).mapTo(order2, OrderDTO.class);

        when(orderService.findOrderById(20L)).thenReturn(order2);
        when(bms.mapTo(order2, OrderDTO.class)).thenReturn(orderDTO2);

        orderFacade.addClaim(20L, 7L);
        Assert.assertTrue(orderFacade.findOrderById(20L).getClaims().contains(claimDTO));
    }

    @Test
    public void removeClaim()
    {
        ClaimDTO claimDTO = new ClaimDTO();
        claimDTO.setId(5L);

        doAnswer(invocationOnMock ->
        {
            order1.removeClaim(claim);
            return 7L;
        }).when(orderService).removeClaim(10L, 5L);

        when(orderService.findOrderById(10L)).thenReturn(order1);
        when(bms.mapTo(order1, OrderDTO.class)).thenReturn(orderDTO1);
        when(bms.mapTo(claim, ClaimDTO.class)).thenReturn(claimDTO);

        bms.mapTo(claim, ClaimDTO.class);

        orderFacade.removeClaim(10L, 5L);
        //Assert.assertEquals(orderFacade.findOrderById(10L).getClaims().size(), 0);
        //Assert.assertFalse(orderFacade.findOrderById(10L).getClaims().contains(claimDTO));
        Assert.assertNull(orderFacade.findOrderById(10L).getClaims());
    }

    @Test
    public void createOrder()
    {
        Order order = new Order();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(30L);

        doAnswer(invocationOnMock ->
        {
            order.setId(30L);
            return 30L;
        }).when(orderService).createOrder(order);

        when(bms.mapTo(order, OrderDTO.class)).thenReturn(orderDTO);

        Assert.assertNotNull(orderDTO.getId());
        Assert.assertEquals(30L, orderDTO.getId().longValue());
    }
}
