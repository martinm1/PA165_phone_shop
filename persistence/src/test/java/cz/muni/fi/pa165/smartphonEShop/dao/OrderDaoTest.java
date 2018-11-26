package cz.muni.fi.pa165.smartphonEShop.dao;

/**
 * @author Jakub
 */
import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.entity.*;
import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import java.time.LocalDate;

import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import org.testng.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.testng.annotations.Test;

import java.util.List;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class OrderDaoTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PhoneDao phoneDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private StockDao stockDao;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @PersistenceContext
    private EntityManager em;

    private Order order1;
    private Order order2;
    private Person person;
    private Phone phone;
    private Stock stock;
    private Address address;

    @BeforeMethod
    public void setUp() {
        address = new Address();
        address.setStreetNumber("3");
        address.setCity("Brno");
        address.setCountry("cz");
        address.setStreetName("hrncirska");
        addressDao.create(address);

        person = new Person();
        person.setFirstName("Franta");
        person.setLastName("Novák");
        person.setAddress(address);
        person.setEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz");
        person.setPhoneNumber("123");
        person.setDateOfBirth(LocalDate.ofYearDay(1, 2));
        person.setGender(Gender.MALE);
        person.setPersonType(PersonType.ADMIN);
        personDao.create(person);

        stock = new Stock();
        stock.setName("tovaren");
        stock.setAddress(address);
        stock.setPhones(phoneDao.findAll());
        stockDao.create(stock);

        phone = new Phone();
        phone.setModelName("S6");
        phone.setPrice(123);
        phone.setTechnicalInfo("info1");
        phone.setManufacturer(Manufacturer.APPLE);
        phone.setStock(stock);
        phoneDao.create(phone);


        order1 = new Order();
        order1.setOrderDate(LocalDate.ofYearDay(2018, 10));
        order1.setPerson(person);
        order1.setPhone(phone);
        order1.setState(OrderState.CREATED);

        order2 = new Order();
        order2.setOrderDate(LocalDate.ofYearDay(2018,11));
        order2.setPerson(person);
        order2.setPhone(phone);
        order2.setState(OrderState.FINISHED);

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

        Assert.assertEquals(order1.getPhone().getId(),
                orderDao.findById(order1.getId()).getPhone().getId());
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
        Assert.assertNull(orderDao.findById(order1.getId()));
        Assert.assertTrue(foundOrders.contains(order2));
    }

    @Test
    public void update() {
        orderDao.create(order1);
        order1.setState(OrderState.FINISHED);
        orderDao.update(order1);

        Assert.assertEquals(OrderState.FINISHED, orderDao.findById(order1.getId()).getState());
    }


    @Test
    public void findOrdersByOrderState() {
        orderDao.create(order1);
        orderDao.create(order2);

        Assert.assertEquals(1, orderDao.findOrdersByOrderState(order1.getState()).size());
        Assert.assertEquals(1, orderDao.findOrdersByOrderState(order2.getState()).size());

        Assert.assertTrue(orderDao.findOrdersByOrderState(order1.getState()).contains(order1));
        Assert.assertTrue(orderDao.findOrdersByOrderState(order2.getState()).contains(order2));
    }

    @Test
    public void findOrdersByOrderDate() {
        orderDao.create(order1);
        orderDao.create(order2);

        Assert.assertEquals(1, orderDao.findOrdersByOrderDate(order1.getOrderDate()).size());
        Assert.assertEquals(1, orderDao.findOrdersByOrderDate(order2.getOrderDate()).size());

        Assert.assertTrue(orderDao.findOrdersByOrderDate(order1.getOrderDate()).contains(order1));
        Assert.assertTrue(orderDao.findOrdersByOrderDate(order2.getOrderDate()).contains(order2));
    }

    @Test
    public void findOrdersByPersonId() {
        orderDao.create(order1);
        orderDao.create(order2);

        Assert.assertEquals(2, orderDao.findOrdersByPersonId(order1.getPerson().getId()).size());

        Assert.assertTrue(orderDao.findOrdersByPersonId(order1.getPerson().getId()).contains(order1));
        Assert.assertTrue(orderDao.findOrdersByPersonId(order1.getPerson().getId()).contains(order2));
    }

    @Test
    public void findOrdersByPhoneId() {
        orderDao.create(order1);
        orderDao.create(order2);

        Assert.assertEquals(2, orderDao.findOrdersByPhoneId(order1.getPhone().getId()).size());

        Assert.assertTrue(orderDao.findOrdersByPhoneId(order1.getPhone().getId()).contains(order1));
        Assert.assertTrue(orderDao.findOrdersByPhoneId(order1.getPhone().getId()).contains(order2));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull()
    {
        orderDao.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNull()
    {
        orderDao.update(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNull()
    {
        orderDao.delete(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNull()
    {
        orderDao.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNegative()
    {
        orderDao.findById((long)-1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findOrdersByPersonIdNull()
    {
        orderDao.findOrdersByPersonId(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findOrdersByPhoneIdNull()
    {
        orderDao.findOrdersByPhoneId(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findOrdersByOrderDateNull()
    {
        orderDao.findOrdersByOrderDate(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findOrdersByOrderStateNull()
    {
        orderDao.findOrdersByOrderState(null);
    }

}
