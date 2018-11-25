package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.OrderDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
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

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

/**
 * Created by Stefan Holecko
 * Class represents: tests for orderService class
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private OrderDao orderDao;

    @Autowired
    @InjectMocks
    private OrderService orderService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Order order1;
    private Order order2;
    private Order order3;

    @BeforeMethod
    public void testPrepare() {
        order1 = new Order();
        order2 = new Order();
        order3 = new Order();

        order1.setId(111L);
        order2.setId(222L);
        order3.setId(333L);

        order1.setOrderDate(LocalDate.now());
        order2.setOrderDate(LocalDate.of(2018, Month.JULY,22));
        order3.setOrderDate(LocalDate.now().plusDays(2));

        order1.setState(OrderState.ACCEPTED);
        order2.setState(OrderState.CREATED);
        order3.setState(OrderState.FINISHED);
    }

    @Test
    public void findOrderByIdTest(){
        when(orderDao.findById(order1.getId())).thenReturn(order1);
        when(orderDao.findById(order2.getId())).thenReturn(order2);
        when(orderDao.findById(order3.getId())).thenReturn(order3);

        Order testOrder1 = orderService.findOrderById(order1.getId());
        Order testOrder2 = orderService.findOrderById(order2.getId());
        Order testOrder3 = orderService.findOrderById(order3.getId());

        Assert.assertEquals(testOrder1, order1);
        Assert.assertEquals(testOrder2, order2);
        Assert.assertEquals(testOrder3, order3);
    }

    @Test
    public void findOrdersByOrderStateTest(){

//        List<Order> orders;
//        when(orderDao.findOrdersByOrderState(OrderState.ACCEPTED);
//        when(orderDao.findOrdersByOrderState(order2.getId())).thenReturn(order2);
//        when(orderDao.findOrdersByOrderState(order3.getId())).thenReturn(order3);
//
//        List<Order> testOrder1 = orderService.findOrdersByOrderState(OrderState.ACCEPTED);
//        Assert.assertEquals(1, testOrder1.size());
//        Assert.assertTrue(testOrder1.contains(order1));
//
//        testOrder1 = orderService.findOrdersByOrderState(OrderState.CREATED);
//        Assert.assertEquals(1, testOrder1.size());
//        Assert.assertTrue(testOrder1.contains(order2));
//
//        testOrder1 = orderService.findOrdersByOrderState(OrderState.FINISHED);
//        Assert.assertEquals(1, testOrder1.size());
//        Assert.assertTrue(testOrder1.contains(order3));
//        //CHANGE STATE?
    }

    @Test
    public void findOrdersByOrderDateTest(){


    }


    @Test
    public void findOrdersByPersonTest(){
    }

    @Test
    public void findOrdersByPhoneTest(){
    }

    @Test
    public void addClaimTest(){
    }

    @Test
    public void removeClaimTest(){
    }

    @Test
    public void createOrderTest(){
    }



    @Test
    public void getAllOrdersTest()
    {
        when(orderDao.findAll()).thenReturn(Stream.of(order1, order2, order3).collect(Collectors.toList()));

        List<Order> orders = orderService.getAllOrders();

        Assert.assertEquals(3, orders.size());
        Assert.assertTrue(orders.contains(order1));
        Assert.assertTrue(orders.contains(order2));
        Assert.assertTrue(orders.contains(order3));
    }


}
