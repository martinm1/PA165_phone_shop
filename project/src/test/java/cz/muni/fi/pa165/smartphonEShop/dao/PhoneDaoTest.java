package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Roman Nahalka
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PhoneDaoTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    private PhoneDao phone;

    @PersistenceContext
    private EntityManager em;

    private Phone phone1;
    private Phone phone2;
    
    @BeforeMethod
    void setUp()
    {
        phone1 = new Phone();
        phone2 = new Phone();

        Address address1 = new Address();
        address1.setStreetNumber("3");
        address1.setCity("Brno");
        address1.setCountry("cz");
        address1.setStreetName("hrncirska");

        Address address2 = new Address();
        address2.setStreetNumber("3");
        address2.setCity("Brno");
        address2.setCountry("cz");
        address2.setStreetName("hrncirska");

        Stock stock1 = new Stock();
        Stock stock2 = new Stock();

        stock1.setName("Hlavni");
        stock2.setName("Vedlejsi");

        stock1.addPhone(phone1);
        stock2.addPhone(phone2);

        stock1.setAddress(address1);
        stock2.setAddress(address2);

        phone1.setModelName("S6");
        phone2.setModelName("S7");

        phone1.setPrice(123);
        phone2.setPrice(345);

        phone1.setTechnicalInfo("info1");
        phone2.setTechnicalInfo("info2");

        phone1.setManufacturer(Manufacturer.APPLE);
        phone2.setManufacturer(Manufacturer.HTC);

        phone1.setStock(stock1);
        phone2.setStock(stock2);
    }

    @Test
    public void findAll()
    {
        phone.create(phone1);
        phone.create(phone2);

        List<Phone> phones = phone.findAll();
        Assert.assertEquals(phones.size(), 2);

        Assert.assertTrue(phones.contains(phone1));
        Assert.assertTrue(phones.contains(phone2));
    }

    @Test
    public void findById()
    {
        phone.create(phone1);
        phone.create(phone2);

        Assert.assertEquals("S6", phone.findById(phone1.getId()).getModelName());
        Assert.assertEquals("S7", phone.findById(phone2.getId()).getModelName());
        Assert.assertNotEquals("S7", phone.findById(phone1.getId()).getModelName());
        Assert.assertNotEquals("S6", phone.findById(phone2.getId()).getModelName());

    }

    @Test
    public void delete()
    {
        phone.create(phone1);
        phone.create(phone2);

        Assert.assertEquals(phone.findAll().size(), 2);
        Assert.assertNotNull(phone.findById(phone1.getId()));
        Assert.assertNotNull(phone.findById(phone2.getId()));

        phone.delete(phone1);
        Assert.assertEquals(phone.findAll().size(), 1);
        Assert.assertFalse(phone.findAll().contains(phone1));
        Assert.assertNull(phone.findById(phone1.getId()));

        phone.delete(phone2);
        Assert.assertEquals(phone.findAll().size(), 0);
        Assert.assertNull(phone.findById(phone2.getId()));
    }

    @Test
    public void update()
    {
        phone.create(phone1);

        phone1.setModelName("S6");
        phone.update(phone1);

        Assert.assertNotEquals("S7", phone.findById(phone1.getId()).getModelName());
        Assert.assertEquals("S6", phone.findById(phone1.getId()).getModelName());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull()
    {
        phone.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNull()
    {
        phone.update(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNull()
    {
        phone.delete(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNull()
    {
        phone.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNegative()
    {
        phone.findById((long)-1);
    }
}
