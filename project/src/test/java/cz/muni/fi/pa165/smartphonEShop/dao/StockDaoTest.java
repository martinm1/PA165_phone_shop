package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by Roman Nahalka
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class StockDaoTest extends AbstractTestNGSpringContextTests 
{
    @Autowired
    private StockDao stock;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void findAll()
    {
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();

        stock1.setName("Hlavni");
        stock2.setName("Vedlejsi");

        stock1.setAddressId(123L);
        stock2.setAddressId(234L);
        
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
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();

        stock1.setName("Hlavni");
        stock2.setName("Vedlejsi");
        
        stock1.setAddressId(123L);
        stock2.setAddressId(234L);

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
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();

        stock1.setName("Hlavni");
        stock2.setName("Vedlejsi");
        
        stock1.setAddressId(123L);
        stock2.setAddressId(234L);

        stock.create(stock1);
        stock.create(stock2);

        List<Stock> stocks = stock.findAll();
        Assert.assertEquals(stocks.size(), 2);

        stock.delete(stock1);
    }

    @Test
    public void update()
    {
        Stock stock1 = new Stock();

        stock1.setName("Hlavni");
        
        stock1.setAddressId(123L);

        stock.create(stock1);

        stock1.setName("Vedlejsi");
        stock.update(stock1);

        Assert.assertNotEquals("Hlavni", stock.findById(stock1.getId()).getName());
        Assert.assertEquals("Vedlejsi", stock.findById(stock1.getId()).getName());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull()
    {
       stock.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNull()
    {
        stock.update(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNull()
    {
        stock.delete(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNull()
    {
        stock.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNegative()
    {
        stock.findById((long)-1);
    }
}
