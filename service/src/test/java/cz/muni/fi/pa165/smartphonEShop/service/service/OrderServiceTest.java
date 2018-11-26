package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.ClaimDao;
import cz.muni.fi.pa165.smartphonEShop.dao.OrderDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Stefan Holecko
 * Class represents: tests for orderService class
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private OrderDao orderDao;

    @Mock
    private ClaimDao claimDao;

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

        order1.setOrderDate(LocalDate.now().plusDays(2));
        order2.setOrderDate(LocalDate.now());
        order3.setOrderDate(LocalDate.now().plusDays(2));

        order1.setState(OrderState.ACCEPTED);
        order2.setState(OrderState.ACCEPTED);
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
        List<Order> orderList = Arrays.asList(order1,order2);
        when(orderDao.findOrdersByOrderState(OrderState.ACCEPTED)).thenReturn(orderList);
        when(orderDao.findOrdersByOrderState(OrderState.FINISHED)).thenReturn(Collections.singletonList(order3));

        List<Order> orders = orderService.findOrdersByOrderState(OrderState.ACCEPTED);

        Assert.assertEquals(2, orders.size());
        Assert.assertTrue(orders.contains(order1));
        Assert.assertTrue(orders.contains(order2));

        orders = orderService.findOrdersByOrderState(OrderState.FINISHED);
        Assert.assertEquals(1, orders.size());
        Assert.assertTrue(orders.contains(order3));
    }

    @Test
    public void findOrdersByOrderDateTest(){
        List<Order> orderList = Arrays.asList(order1,order3);
        when(orderDao.findOrdersByOrderDate(LocalDate.now().plusDays(2))).thenReturn(orderList);
        when(orderDao.findOrdersByOrderDate(LocalDate.now())).thenReturn(Collections.singletonList(order2));

        List<Order> orders = orderService.findOrdersByOrderDate(LocalDate.now().plusDays(2));

        Assert.assertEquals(2, orders.size());
        Assert.assertTrue(orders.contains(order1));
        Assert.assertTrue(orders.contains(order3));

        orders = orderService.findOrdersByOrderDate(LocalDate.now());
        Assert.assertEquals(1, orders.size());
        Assert.assertTrue(orders.contains(order2));
    }


    @Test
    public void findOrdersByPhoneIdTest(){
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        phone1.setId(11L);
        phone2.setId(22L);

        order1.setPhone(phone1);
        order2.setPhone(phone1);
        order3.setPhone(phone2);

        List<Order> orderList = Arrays.asList(order1,order2);
        when(orderDao.findOrdersByPhoneId(phone1.getId())).thenReturn(orderList);
        when(orderDao.findOrdersByPhoneId(phone2.getId())).thenReturn(Collections.singletonList(order3));

        List<Order> orders = orderService.findOrdersByPhoneId(phone1.getId());

        Assert.assertEquals(2, orders.size());
        Assert.assertTrue(orders.contains(order1));
        Assert.assertTrue(orders.contains(order2));

        orders = orderService.findOrdersByPhoneId(phone2.getId());
        Assert.assertEquals(1, orders.size());
        Assert.assertTrue(orders.contains(order3));
    }

    @Test
    public void findOrdersByPersonIdTest(){
        Person person1 = new Person();
        Person person2 = new Person();

        person1.setId(11L);
        person2.setId(22L);

        order1.setPerson(person1);
        order2.setPerson(person1);
        order3.setPerson(person2);

        List<Order> orderList = Arrays.asList(order1,order2);
        when(orderDao.findOrdersByPersonId(person1.getId())).thenReturn(orderList);
        when(orderDao.findOrdersByPersonId(person2.getId())).thenReturn(Collections.singletonList(order3));

        List<Order> orders = orderService.findOrdersByPersonId(person1.getId());

        Assert.assertEquals(2, orders.size());
        Assert.assertTrue(orders.contains(order1));
        Assert.assertTrue(orders.contains(order2));

        orders = orderService.findOrdersByPersonId(person2.getId());
        Assert.assertEquals(1, orders.size());
        Assert.assertTrue(orders.contains(order3));

    }

    @Test
    public void addClaimTest(){
        Claim claim = new Claim();
        Assert.assertTrue(!order1.getClaims().contains(claim));

        when(claimDao.findById(claim.getId())).thenReturn(claim);
        when(orderDao.findById(order1.getId())).thenReturn(order1);

        orderService.addClaim(order1.getId(), claim.getId());

        Assert.assertTrue(order1.getClaims().contains(claim));

    }

    @Test
    public void removeClaimTest(){
        Claim claim = new Claim();
        Assert.assertTrue(!order1.getClaims().contains(claim));

        when(claimDao.findById(claim.getId())).thenReturn(claim);
        when(orderDao.findById(order1.getId())).thenReturn(order1);

        orderService.addClaim(order1.getId(), claim.getId());

        Assert.assertTrue(order1.getClaims().contains(claim));

        orderService.removeClaim(order1.getId(), claim.getId());

        Assert.assertTrue(!order1.getClaims().contains(claim));

    }

    @Test
    public void createOrderTest(){
        Order order = new Order();

        doAnswer(invocationOnMock ->
        {
            order.setId(40L);
            return 40L;
        }).when(orderDao).create(order);

        orderService.createOrder(order);
        Assert.assertNotNull(order.getId());
        Assert.assertEquals(40L, order.getId().longValue());

    }

    @Test
    public void getAllOrdersTest() {
        when(orderDao.findAll()).thenReturn(Stream.of(order1, order2, order3).collect(Collectors.toList()));

        List<Order> orders = orderService.getAllOrders();

        Assert.assertEquals(3, orders.size());
        Assert.assertTrue(orders.contains(order1));
        Assert.assertTrue(orders.contains(order2));
        Assert.assertTrue(orders.contains(order3));
    }


}
