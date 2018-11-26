package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.entity.*;
//import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.*;
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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Stefan Holecko
 * Class represents:
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ClaimDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ClaimDao claimDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PhoneDao phoneDao;

    @Autowired
    private StockDao stockDao;

    private Claim claim1;
    private Claim claim2;
    private Order order1;
    private Order order2;

    private Person person;
    private Address address;
    private Stock stock;
    private Phone phone;


    @PersistenceContext
    private EntityManager em;


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
        orderDao.create(order1);

        order2 = new Order();
        order2.setOrderDate(LocalDate.ofYearDay(2018,10));
        order2.setPerson(person);
        order2.setPhone(phone);
        order2.setState(OrderState.CREATED);
        orderDao.create(order2);

        claim1 = new Claim();
        claim1.setOrder(order1);
        claim1.setClaimState(ClaimState.ACCEPTED);
        claim1.setReasonOfClaim("rozbity");
        claim1.setWantedSolutionByCustomer(ClaimSolution.REPAIR);
        claim1.setTechnicalReport("chyba");

        claim2 = new Claim();
        claim2.setOrder(order2);
        claim2.setClaimState(ClaimState.CREATED);
        claim2.setReasonOfClaim("rozbity2");
        claim2.setWantedSolutionByCustomer(ClaimSolution.MONEY);
        claim2.setTechnicalReport("vratene peniaze");

    }

    @Test
    public void findAll() {
        claimDao.create(claim1);
        claimDao.create(claim2);

        List<Claim> claimList = claimDao.findAll();
        Assert.assertEquals(claimList.size(), 2);
        Assert.assertTrue(claimList.contains(claim1));
        Assert.assertTrue(claimList.contains(claim2));
    }

    @Test
    public void findById() {

        claimDao.create(claim1);
        claimDao.create(claim2);
        
        Assert.assertEquals(claim1.getOrder(), claimDao.findById(claim1.getId()).getOrder());
        Assert.assertEquals(claim2.getOrder(), claimDao.findById(claim2.getId()).getOrder());
    }

    @Test
    public void findClaimByOrderId() {
        claimDao.create(claim1);
        claimDao.create(claim2);

        Assert.assertEquals(1, claimDao.findClaimByOrderId(claim1.getOrder().getId()).size());
        Assert.assertEquals(1, claimDao.findClaimByOrderId(claim2.getOrder().getId()).size());

        Assert.assertTrue(claimDao.findClaimByOrderId(claim1.getOrder().getId()).contains(claim1));
        Assert.assertTrue(claimDao.findClaimByOrderId(claim2.getOrder().getId()).contains(claim2));

    }
    @Test
    public void findClaimByUserId() {
        personDao.create(person);
        claimDao.create(claim1);

        Assert.assertEquals(1, claimDao.findClaimByUserId(person.getId()).size());
        Assert.assertTrue(claimDao.findClaimByUserId(person.getId()).contains(claim1));
    }
    
    @Test
    public void findClaimByClaimState() {
        claimDao.create(claim1);
        claimDao.create(claim2);

        Assert.assertEquals(1, claimDao.findClaimByClaimState(claim1.getClaimState()).size());
        Assert.assertEquals(1, claimDao.findClaimByClaimState(claim1.getClaimState()).size());

        Assert.assertTrue(claimDao.findClaimByClaimState(claim1.getClaimState()).contains(claim1));
        Assert.assertTrue(claimDao.findClaimByClaimState(claim2.getClaimState()).contains(claim2));
    }
    @Test
    public void findClaimByClaimSolution() {
        claimDao.create(claim1);
        claimDao.create(claim2);

        Assert.assertTrue(claimDao.findClaimByClaimSolution(claim1.getWantedSolutionByCustomer()).contains(claim1));
        Assert.assertTrue(claimDao.findClaimByClaimSolution(claim2.getWantedSolutionByCustomer()).contains(claim2));

        Assert.assertEquals(1, claimDao.findClaimByClaimSolution(claim1.getWantedSolutionByCustomer()).size());
        Assert.assertEquals(1, claimDao.findClaimByClaimSolution(claim1.getWantedSolutionByCustomer()).size());
    }

        @Test
    public void create() {

        List<Claim> claimList = claimDao.findAll();
        Assert.assertEquals(claimList.size(), 0);

        claimDao.create(claim1);
        claimList = claimDao.findAll();
        Assert.assertEquals(claimList.size(), 1);

        claimDao.create(claim2);
        claimList = claimDao.findAll();
        Assert.assertEquals(claimList.size(), 2);
    }

    @Test
    public void delete() {
        claimDao.create(claim1);
        claimDao.create(claim2);

        Assert.assertEquals(claimDao.findAll().size(), 2);
        Assert.assertNotNull(claimDao.findById(claim1.getId()));
        Assert.assertNotNull(claimDao.findById(claim2.getId()));
        claimDao.delete(claim1);
        Assert.assertEquals(claimDao.findAll().size(), 1);
        Assert.assertFalse(claimDao.findAll().contains(claim1));
        Assert.assertNull(claimDao.findById(claim1.getId()));
        claimDao.delete(claim2);
        Assert.assertEquals(claimDao.findAll().size(), 0);
        Assert.assertNull(claimDao.findById(claim2.getId()));
    }
    @Test
    public void update() {

        claimDao.create(claim1);

        claim1.setWantedSolutionByCustomer(ClaimSolution.REPAIR);
        claimDao.update(claim1);

        Assert.assertNotEquals(ClaimSolution.MONEY, claimDao.findById(claim1.getId()).getWantedSolutionByCustomer());
        Assert.assertEquals(ClaimSolution.REPAIR, claimDao.findById(claim1.getId()).getWantedSolutionByCustomer());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNull() {
        claimDao.update(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNull() {
        claimDao.delete(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNull() {
        claimDao.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNegative() {
        claimDao.findById(-1L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull(){
        claimDao.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findClaimByOrderIdNull(){
        claimDao.findClaimByOrderId(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findClaimByUserIdNull(){
        claimDao.findClaimByUserId(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findClaimByClaimSolutionNull(){
        claimDao.findClaimByClaimSolution(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findClaimByClaimStateNull(){
        claimDao.findClaimByClaimState(null);
    }


}
