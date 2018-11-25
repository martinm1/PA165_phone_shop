/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.StockDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author martin
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class StockServiceTest {
    @Mock
    private StockDao stockDao;

    @Autowired
    @InjectMocks
    private StockServiceImpl stockService;

    private Stock stock1;
    private Stock stock2;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void prepare()
    {
        stock1 = new Stock();
        stock2 = new Stock();

        stock1.setId(10L);
        stock2.setId(20L);

        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

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

        stock1.addPhone(phone1);
        stock2.addPhone(phone2);

        stock1.setAddress(address1);
        stock2.setAddress(address2);
    }
    
    @Test
    public void createStock()
    {
        Stock stock = new Stock();

        doAnswer(invocationOnMock ->
        {
            stock.setId(30L);
            return 30L;
        }).when(stockDao).create(stock);

        stockService.createStock(stock);
        Assert.assertNotNull(stock.getId());
        Assert.assertEquals(30L, stock.getId().longValue());
    }
    
    @Test
    public void findStockById()
    {
        when(stockDao.findById(stock1.getId())).thenReturn(stock1);
        when(stockDao.findById(stock2.getId())).thenReturn(stock2);

        Stock stock = stockService.findStockById(stock1.getId());
        Stock stock3 = stockService.findStockById(stock2.getId());

        Assert.assertEquals(stock, stock1);
        Assert.assertEquals(stock3, stock2);
    }
    
    @Test
    public void findStockByName()
    {
        /*when(stockDao.findByName(stock1.getId())).thenReturn(stock1);
        when(stockDao.findByName(stock2.getId())).thenReturn(stock2);

        Stock stock = stockService.findStockById(stock1.getId());
        Stock stock3 = stockService.findStockById(stock2.getId());

        Assert.assertEquals(stock, stock1);
        Assert.assertEquals(stock3, stock2);*/
        
        //the methods i need are not implemented yet
    }
}
