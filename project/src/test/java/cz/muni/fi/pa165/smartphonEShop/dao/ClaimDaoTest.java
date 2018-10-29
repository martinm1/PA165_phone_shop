package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
//import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
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

    private Claim claim1;
    private Claim claim2;
    private Order order1;
    private Order order2;


    @PersistenceContext
    private EntityManager em;


    @BeforeMethod
    public void setUp() {
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
        Claim claim1 = new Claim();
        Claim claim2 = new Claim();
        //Order order1 = new Order();
        //Order order2 = new Order();
        
        Long orderId1 = 123L;
        Long orderId2 = 234L;

        //claim1.setOrder(order1);
        //claim2.setOrder(order2);
        
        claim1.setOrderId(orderId1);
        claim2.setOrderId(orderId2);
        
        claim1.setReasonOfClaim("not working");
        claim2.setReasonOfClaim("broken");
        
        claim1.setClaimState(ClaimState.CREATED);
        claim2.setClaimState(ClaimState.ACCEPTED);

        claim1.setWantedSolutionByCustomer(ClaimSolution.MONEY);
        claim2.setWantedSolutionByCustomer(ClaimSolution.REPAIR);

        claim1.setReasonOfClaim("reason 1");
        claim2.setReasonOfClaim("reason 2");

        claim1.setClaimState(ClaimState.ACCEPTED);
        claim2.setClaimState(ClaimState.CREATED);

        claim.create(claim1);
        claim.create(claim2);

        List<Claim> claimList = claim.findAll();
        Assert.assertEquals(claimList.size(), 2);
        Assert.assertTrue(claimList.contains(claim1));
        Assert.assertTrue(claimList.contains(claim2));
    }

    @Test
    public void findById() {
        Claim claim1 = new Claim();
        Claim claim2 = new Claim();
        //Order order1 = new Order();
        //Order order2 = new Order();
        
        Long orderId1 = 123L;
        Long orderId2 = 234L;

        //claim1.setOrder(order1);
        //claim2.setOrder(order2);
        
        claim1.setOrderId(orderId1);
        claim2.setOrderId(orderId2);
        
        claim1.setReasonOfClaim("not working");
        claim2.setReasonOfClaim("broken");
        
        claim1.setClaimState(ClaimState.CREATED);
        claim2.setClaimState(ClaimState.ACCEPTED);
        
        claim1.setWantedSolutionByCustomer(ClaimSolution.MONEY);
        claim2.setWantedSolutionByCustomer(ClaimSolution.REPAIR);

        claim1.setReasonOfClaim("reason 1");
        claim2.setReasonOfClaim("reason 2");

        claim1.setClaimState(ClaimState.ACCEPTED);
        claim2.setClaimState(ClaimState.CREATED);

        claim.create(claim1);
        claim.create(claim2);

        //Assert.assertEquals(order1, claim.findById(claim1.getId()).getOrder()); // TODO porovnavat podla equals?
        //Assert.assertEquals(order2, claim.findById(claim2.getId()).getOrder());
        //Assert.assertNotEquals(order1, claim.findById(claim2.getId()).getOrder());
        //Assert.assertNotEquals(order2, claim.findById(claim1.getId()).getOrder());
        
        Assert.assertEquals(orderId1, claim.findById(claim1.getId()).getOrderId()); // TODO porovnavat podla equals?
        Assert.assertEquals(orderId2, claim.findById(claim2.getId()).getOrderId());
    }

    @Test
    public void create() {
        Claim claim1 = new Claim();
        Claim claim2 = new Claim();
        //Order order1 = new Order();
        //Order order2 = new Order();
        
        Long orderId1 = 123L;
        Long orderId2 = 234L;

        //claim1.setOrder(order1);
        //claim2.setOrder(order2);
        
        claim1.setOrderId(orderId1);
        claim2.setOrderId(orderId2);
        
        claim1.setReasonOfClaim("not working");
        claim2.setReasonOfClaim("broken");
        
        claim1.setClaimState(ClaimState.CREATED);
        claim2.setClaimState(ClaimState.ACCEPTED);

        claim1.setWantedSolutionByCustomer(ClaimSolution.MONEY);
        claim2.setWantedSolutionByCustomer(ClaimSolution.REPAIR);

        claim1.setReasonOfClaim("reason 1");
        claim2.setReasonOfClaim("reason 2");

        claim1.setClaimState(ClaimState.ACCEPTED);
        claim2.setClaimState(ClaimState.CREATED);

        List<Claim> claimList = claim.findAll();
        Assert.assertEquals(claimList.size(), 0);


        claim.create(claim1);

        claimList = claim.findAll();
        Assert.assertEquals(claimList.size(), 1);

        claim.create(claim2);

        claimList = claim.findAll();
        Assert.assertEquals(claimList.size(), 2);
    }

    @Test
    public void delete() {
        Claim claim1 = new Claim();
        Claim claim2 = new Claim();
        //Order order1 = new Order();
        //Order order2 = new Order();
        
        Long orderId1 = 123L;
        Long orderId2 = 234L;

        //claim1.setOrder(order1);
        //claim2.setOrder(order2);
        
        claim1.setOrderId(orderId1);
        claim2.setOrderId(orderId2);
        
        claim1.setReasonOfClaim("not working");
        claim2.setReasonOfClaim("broken");
        
        claim1.setClaimState(ClaimState.CREATED);
        claim2.setClaimState(ClaimState.ACCEPTED);

        claim1.setWantedSolutionByCustomer(ClaimSolution.MONEY);
        claim2.setWantedSolutionByCustomer(ClaimSolution.REPAIR);

        claim1.setReasonOfClaim("reason 1");
        claim2.setReasonOfClaim("reason 2");

        claim1.setClaimState(ClaimState.ACCEPTED);
        claim2.setClaimState(ClaimState.CREATED);

        claim.create(claim1);
        claim.create(claim2);


        Assert.assertEquals(claim.findAll().size(), 2);
        Assert.assertNotNull(claim.findById(claim1.getId()));
        Assert.assertNotNull(claim.findById(claim2.getId()));
        claim.delete(claim1);
        Assert.assertEquals(claim.findAll().size(), 1);
        Assert.assertFalse(claim.findAll().contains(claim1));
        Assert.assertNull(claim.findById(claim1.getId()));
        claim.delete(claim2);
        Assert.assertEquals(claim.findAll().size(), 0);
        Assert.assertNull(claim.findById(claim2.getId()));
    }
    @Test
    public void update() {
        Claim claim1 = new Claim();
        //Order order1 = new Order();
        
        Long orderId1 = 123L;

        //claim1.setOrder(order1);
        
        claim1.setOrderId(orderId1);
        
        claim1.setReasonOfClaim("not working");
        
        claim1.setClaimState(ClaimState.CREATED);
        
        claim1.setWantedSolutionByCustomer(ClaimSolution.MONEY);
        claim1.setReasonOfClaim("reason 1");
        claim1.setClaimState(ClaimState.ACCEPTED);
        claim.create(claim1);

        claim1.setWantedSolutionByCustomer(ClaimSolution.REPAIR);
        claim.update(claim1);

        Assert.assertNotEquals(ClaimSolution.MONEY, claim.findById(claim1.getId()).getWantedSolutionByCustomer());
        Assert.assertEquals(ClaimSolution.REPAIR, claim.findById(claim1.getId()).getWantedSolutionByCustomer());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNull() {
        claim.update(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNull() {
        claim.delete(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNull() {
        claim.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNegative() {
        claim.findById(-1L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull(){
        claim.create(null);
    }


}
