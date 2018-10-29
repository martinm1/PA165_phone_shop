package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
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
    
    @Test
    public void findAll()
    {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        phone1.setModelName("S6");
        phone2.setModelName("S7");
        
        phone1.setPrice(123);
        phone2.setPrice(345);
        
        phone1.setTechnicalInfo("info1");
        phone2.setTechnicalInfo("info2");
        
        phone1.setManufacturer(Manufacturer.APPLE);
        phone2.setManufacturer(Manufacturer.HTC);
        
        phone1.setStockId(10L);
        phone2.setStockId(12L);

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
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        phone1.setModelName("S6");
        phone2.setModelName("S7");
        
        phone1.setPrice(123);
        phone2.setPrice(345);
        
        phone1.setTechnicalInfo("info1");
        phone2.setTechnicalInfo("info2");
        
        phone1.setManufacturer(Manufacturer.APPLE);
        phone2.setManufacturer(Manufacturer.HTC);
        
        phone1.setStockId(10L);
        phone2.setStockId(12L);

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
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        phone1.setModelName("S6");
        phone2.setModelName("S7");
        
        phone1.setPrice(123);
        phone2.setPrice(345);
        
        phone1.setTechnicalInfo("info1");
        phone2.setTechnicalInfo("info2");
        
        phone1.setManufacturer(Manufacturer.APPLE);
        phone2.setManufacturer(Manufacturer.HTC);
        
        phone1.setStockId(10L);
        phone2.setStockId(12L);

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
        Phone phone1 = new Phone();

        phone1.setModelName("S7");
        
        phone1.setPrice(123);
        
        phone1.setTechnicalInfo("info1");
        
        phone1.setManufacturer(Manufacturer.APPLE);
        
        phone1.setStockId(10L);

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
