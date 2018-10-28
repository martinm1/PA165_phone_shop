package cz.muni.fi.pa165.smartphonEShop.dao;

/**
 * @author Jakub
 */
import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class OrderDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private OrderDao orderDao;

    private Order order1;
    private Order order2;
    private Order order3;

    @BeforeMethod
    public void setUp() {
        order1 = new Order();
        order1.setOrderDate(LocalDate.ofYearDay(2018, 10));
        order1.setPersonId(1l);
        order1.setPhoneId(1l);
        order1.setState(OrderState.CREATED);

        order2 = new Order();
        order2.setOrderDate(LocalDate.ofYearDay(2018,10));
        order2.setPersonId(2l);
        order2.setPhoneId(2l);
        order2.setState(OrderState.CREATED);

        order3 = new Order();
        order3.setOrderDate(LocalDate.ofYearDay(2018,10));
        order3.setPersonId(3l);
        order3.setPhoneId(3l);
        order3.setState(OrderState.CREATED);

    }

    @Test
    public void create() {
    orderDao.create(order1);
        Assert.assertNotNull(order1.getId());
    }

    @Test
    public void findAll() {
        orderDao.create(order1);
        orderDao.create(order2);

        List<Order> foundOrders = orderDao.findAll();
        Assert.assertEquals(foundOrders.size(), 2);
        Assert.assertTrue(foundOrders.contains(order1));
        Assert.assertTrue(foundOrders.contains(order2));
    }


    @Test
    public void findById() {
        orderDao.create(order1);

        Assert.assertEquals(order1.getPhoneId(),orderDao.findById(order1.getId()).getPhoneId());
        Assert.assertNull(orderDao.findById(4l));
    }

    @Test
    public void delete() {
        orderDao.create(order1);
        orderDao.create(order2);

        List<Order> foundOrders = orderDao.findAll();
        Assert.assertEquals(foundOrders.size(), 2);
        Assert.assertTrue(foundOrders.contains(order1));
        Assert.assertTrue(foundOrders.contains(order2));

        orderDao.delete(order1);

        foundOrders = orderDao.findAll();
        Assert.assertEquals(foundOrders.size(), 1);
        Assert.assertNull(orderDao.findById(1l));
        Assert.assertTrue(foundOrders.contains(order2));
    }

    @Test
    public void update() {
        orderDao.create(order1);
        order1.setState(OrderState.FINISHED);
        orderDao.update(order1);

        Assert.assertEquals(OrderState.FINISHED, orderDao.findById(order1.getId()).getState());
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull()
    {
        orderDao.create(null);
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNull()
    {
        orderDao.update(null);
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNull()
    {
        orderDao.delete(null);
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNull()
    {
        orderDao.findById(null);
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNegative()
    {
        orderDao.findById((long)-1);
    }
}
