package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import java.util.List;
/**
 *
 * @author martin
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AddressDaoTest extends  AbstractTestNGSpringContextTests{
    @Autowired
    private AddressDao address;

    @Test
    public void findAll()
    {
        Address address1 = new Address();
        Address address2 = new Address();

        address1.setStreetName("Botanická");
        address2.setStreetName("Ilkovičova");

        address1.setStreetNumber("68A");
        address2.setStreetNumber("2");
        
        address1.setCity("Brno");
        address2.setCity("Bratislava");
        
        address1.setCountry("Česko");
        address2.setCountry("Slovensko");
         
        address.create(address1);
        address.create(address2);

        List<Address> addresses = address.findAll();
        Assert.assertEquals(addresses.size(), 2);
        Assert.assertTrue(addresses.contains(address1));
        Assert.assertTrue(addresses.contains(address2));
    }

    @Test
    public void findById()
    {
        Address address1 = new Address();
        Address address2 = new Address();

        address1.setStreetName("Botanická");
        address2.setStreetName("Ilkovičova");

        address1.setStreetNumber("68A");
        address2.setStreetNumber("2");
        
        address1.setCity("Brno");
        address2.setCity("Bratislava");
        
        address1.setCountry("Česko");
        address2.setCountry("Slovensko");
         
        address.create(address1);
        address.create(address2);

        Assert.assertEquals("Botanická", address.findById(address1.getId()).getStreetName());
        Assert.assertEquals("Ilkovičova", address.findById(address2.getId()).getStreetName());
        Assert.assertNotEquals("Botanická", address.findById(address1.getId()).getStreetName());
        Assert.assertNotEquals("Ilkovičova", address.findById(address2.getId()).getStreetName());

    }

    @Test
    public void delete()
    {
        Address address1 = new Address();
        Address address2 = new Address();

        address1.setStreetName("Botanická");
        address2.setStreetName("Ilkovičova");

        address1.setStreetNumber("68A");
        address2.setStreetNumber("2");
        
        address1.setCity("Brno");
        address2.setCity("Bratislava");
        
        address1.setCountry("Česko");
        address2.setCountry("Slovensko");
         
        address.create(address1);
        address.create(address2);

        Assert.assertEquals(address.findAll(), 2);
        Assert.assertNotNull(address.findById(address1.getId()));
        Assert.assertNotNull(address.findById(address2.getId()));

        address.delete(address1);
        Assert.assertEquals(address.findAll(), 1);
        Assert.assertFalse(address.findAll().contains(address1));
        Assert.assertNull(address.findById(address1.getId()));

        address.delete(address2);
        Assert.assertEquals(address.findAll(), 0);
        Assert.assertNull(address.findById(address2.getId()));
    }

    @Test
    public void update()
    {
        Address address1 = new Address();

        address1.setStreetName("Botanická");

        address1.setStreetNumber("68A");
        
        address1.setCity("Brno");
        
        address1.setCountry("Česko");
         
        address.create(address1);

        address1.setStreetNumber("666");
        address.update(address1);

        Assert.assertEquals("666", address.findById(address1.getId()).getStreetNumber());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull()
    {
        address.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNull()
    {
        address.update(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNull()
    {
        address.delete(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNull()
    {
        address.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNegative()
    {
        address.findById((long)-1);
    }
}
