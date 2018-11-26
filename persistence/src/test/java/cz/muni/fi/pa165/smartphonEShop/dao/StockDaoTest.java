package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.exceptions.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by Roman Nahalka
 * Class represents: Test for Stock dao.
 * @author xnahalka
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class StockDaoTest extends AbstractTestNGSpringContextTests 
{
    @Autowired
    private StockDao stock;

    @Autowired
    private PhoneDao phoneDao;

    @Autowired
    private AddressDao addressDao;

    @PersistenceContext
    private EntityManager em;

    private Stock stock1;
    private Stock stock2;

    private Address address1;
    private Address address2;

    private Phone phone1;
    private Phone phone2;

    @BeforeMethod
    void setUp()
    {
        stock1 = new Stock();
        stock2 = new Stock();
        phone1 = new Phone();
        phone2 = new Phone();


        address1 = new Address();
        address1.setStreetNumber("3");
        address1.setCity("Brno");
        address1.setCountry("cz");
        address1.setStreetName("hrncirska");

        address2 = new Address();
        address2.setStreetNumber("3");
        address2.setCity("Brno");
        address2.setCountry("cz");
        address2.setStreetName("hrncirska");

        addressDao.create(address1);
        addressDao.create(address2);

        stock1.setName("Hlavni");
        stock2.setName("Vedlejsi");

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

        phoneDao.create(phone1);
        phoneDao.create(phone2);


        stock1.addPhone(phone1);
        stock2.addPhone(phone2);

        stock1.setAddress(address1);
        stock2.setAddress(address2);

    }

    @Test
    public void create()
    {
        stock.create(stock1);
        Assert.assertNotNull(stock1.getId());
    }

    @Test
    public void findAll()
    {
        stock.create(stock1);
        stock.create(stock2);

        List<Stock> stocks = stock.findAll();
        Assert.assertEquals(stocks.size(), 2);

        Assert.assertTrue(stocks.contains(stock1));
        Assert.assertTrue(stocks.contains(stock2));
    }

    @Test
    public void findById()
    {
        stock.create(stock1);
        stock.create(stock2);

        Assert.assertEquals("Hlavni", stock.findById(stock1.getId()).getName());
        Assert.assertEquals("Vedlejsi", stock.findById(stock2.getId()).getName());
        Assert.assertNotEquals("Vedlejsi", stock.findById(stock1.getId()).getName());
        Assert.assertNotEquals("Hlavni", stock.findById(stock2.getId()).getName());
    }

    @Test
    public void delete()
    {
        stock.create(stock1);
        stock.create(stock2);

        List<Stock> stocks = stock.findAll();
        Assert.assertEquals(stocks.size(), 2);

        stock.delete(stock1);
    }

    @Test
    public void update()
    {
        stock.create(stock1);

        stock1.setName("Vedlejsi");
        stock.update(stock1);

        Assert.assertNotEquals("Hlavni", stock.findById(stock1.getId()).getName());
        Assert.assertEquals("Vedlejsi", stock.findById(stock1.getId()).getName());
    }

    @Test(expectedExceptions = DAOException.class)
    public void createNull()
    {
       stock.create(null);
    }

    @Test(expectedExceptions = DAOException.class)
    public void updateNull()
    {
        stock.update(null);
    }

    @Test(expectedExceptions = DAOException.class)
    public void deleteNull()
    {
        stock.delete(null);
    }

    @Test(expectedExceptions = DAOException.class)
    public void findByIdNull()
    {
        stock.findById(null);
    }

    @Test(expectedExceptions = DAOException.class)
    public void findByIdNegative()
    {
        stock.findById((long)-1);
    }

    @Test
    public void findByPhoneId()
    {
        stock.create(stock1);
        stock.create(stock2);

        Assert.assertEquals(stock.findByPhoneId(phone1.getId()), stock1);
        Assert.assertEquals(stock.findByPhoneId(phone2.getId()), stock2);
    }

    @Test
    public void findByAddressId()
    {
        stock.create(stock1);
        stock.create(stock2);

        Assert.assertEquals(stock.findByAddressId(address1.getId()), stock1);
        Assert.assertEquals(stock.findByAddressId(address2.getId()), stock2);
    }
}
