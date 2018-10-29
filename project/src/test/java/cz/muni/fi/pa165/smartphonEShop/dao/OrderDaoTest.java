package cz.muni.fi.pa165.smartphonEShop.dao;

/**
 * @author Jakub
 */
import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @PersistenceContext
    private EntityManager em;

    private Order order1;
    private Order order2;
    private Person person;
    private Phone phone;

    @BeforeMethod
    public void setUp() {
        person = new Person();
        person.setFirstName("Franta");
        person.setLastName("Novák");
        person.setAddressId(123L);
        person.setEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz");
        person.setPhoneNumber("123");
        person.setDateOfBirth(LocalDate.ofYearDay(1, 2));
        person.setGender(Gender.MALE);
        person.setPersonType(PersonType.ADMIN);
        personDao.create(person);

        phone = new Phone();
        phone.setModelName("S6");
        phone.setPrice(123);
        phone.setTechnicalInfo("info1");
        phone.setManufacturer(Manufacturer.APPLE);
        phone.setStockId(10L);
        phoneDao.create(phone);


        order1 = new Order();
        order1.setOrderDate(LocalDate.ofYearDay(2018, 10));
        order1.setPerson(person);
        order1.setPhone(phone);
        order1.setState(OrderState.CREATED);

        order2 = new Order();
        order2.setOrderDate(LocalDate.ofYearDay(2018,10));
        order2.setPerson(person);
        order2.setPhone(phone);
        order2.setState(OrderState.CREATED);

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
