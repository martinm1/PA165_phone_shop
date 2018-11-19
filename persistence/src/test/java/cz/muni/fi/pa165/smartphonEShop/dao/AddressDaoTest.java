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
import org.testng.annotations.BeforeMethod;
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
    
    @Autowired
    private StockDao stock;
    
    private Address address1;
    private Address address2;
    
    @BeforeMethod
    public void setUp(){
        
        
        address1 = new Address();
        address2 = new Address();

        address1.setStreetName("Botanická");
        address2.setStreetName("Ilkovičova");

        address1.setStreetNumber("68A");
        address2.setStreetNumber("2");
        
        address1.setCity("Brno");
        address2.setCity("Bratislava");
        
        address1.setCountry("Česko");
        address2.setCountry("Slovensko");
    }
    
    @Test
    public void create() {

        List<Address> addresses = address.findAll();
        Assert.assertEquals(addresses.size(), 0);

        address.create(address1);
        addresses = address.findAll();
        Assert.assertEquals(addresses.size(), 1);

        address.create(address2);
        addresses = address.findAll();
        Assert.assertEquals(addresses.size(), 2);
    }

    @Test
    public void findAll()
    {
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
        address.create(address1);
        address.create(address2);

        Assert.assertEquals(address1, address.findById(address1.getId()));
        Assert.assertEquals(address2, address.findById(address2.getId()));

    }

    @Test
    public void delete()
    {
        address.create(address1);
        address.create(address2);

        Assert.assertEquals(address.findAll().size(), 2);
        Assert.assertNotNull(address.findById(address1.getId()));
        Assert.assertNotNull(address.findById(address2.getId()));

        address.delete(address1);
        Assert.assertEquals(address.findAll().size(), 1);
        Assert.assertFalse(address.findAll().contains(address1));
        Assert.assertNull(address.findById(address1.getId()));

        address.delete(address2);
        Assert.assertEquals(address.findAll().size(), 0);
        Assert.assertNull(address.findById(address2.getId()));
    }

    @Test
    public void update()
    {
        address.create(address1);

        address1.setStreetNumber("666");
        address.update(address1);

        Assert.assertEquals(address1, address.findById(address1.getId()));
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
