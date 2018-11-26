package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.AddressDao;
import cz.muni.fi.pa165.smartphonEShop.dao.PhoneDao;
import cz.muni.fi.pa165.smartphonEShop.dao.StockDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import java.util.Arrays;
import java.util.List;
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
    
    @Mock
    private AddressDao addressDao;
    
    @Mock
    private PhoneDao phoneDao;

    @Autowired
    @InjectMocks
    private StockServiceImpl stockService;

    private Stock stock1;
    private Stock stock2;
    
    private Address address1;
    private Address address2;
    
    private Phone phone1;
    private Phone phone2;

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

        phone1 = new Phone();
        phone2 = new Phone();

        address1 = new Address();
        address1.setStreetNumber("3");
        address1.setCity("Brno");
        address1.setCountry("cz");
        address1.setStreetName("hrncirska");
        address1.setId(21L);
        
        address2 = new Address();
        address2.setStreetNumber("3");
        address2.setCity("Brno");
        address2.setCountry("cz");
        address2.setStreetName("hrncirska");
        address2.setId(22L);
                
        address1.setStock(stock1);
        address2.setStock(stock2);

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
        
        phone1.setId(23L);
        phone2.setId(24L);
        
        phone1.setStock(stock1);
        phone2.setStock(stock2);

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
        when(stockDao.findByName(stock1.getName())).thenReturn(stock1);
        when(stockDao.findByName(stock2.getName())).thenReturn(stock2);

        Stock stock = stockService.findStockByName(stock1.getName());
        Stock stock3 = stockService.findStockByName(stock2.getName());

        Assert.assertEquals(stock, stock1);
        Assert.assertEquals(stock3, stock2);
        
    }
    
    @Test
    public void findStockByAddressId()
    {
        when(addressDao.findById(address1.getId())).thenReturn(address1);
        when(addressDao.findById(address2.getId())).thenReturn(address2);

        Stock stock = stockService.findStockByAddressId(stock1.getAddress().getId());
        Stock stock3 = stockService.findStockByAddressId(stock2.getAddress().getId());

        Assert.assertEquals(stock, stock1);
        Assert.assertEquals(stock3, stock2);
        
    }
    
    @Test
    public void findStockByPhoneId()
    {
        when(phoneDao.findById(phone1.getId())).thenReturn(phone1);
        when(phoneDao.findById(phone2.getId())).thenReturn(phone2);

        Stock stock = stockService.findStockByPhoneId(phone1.getId());
        Stock stock3 = stockService.findStockByPhoneId(phone2.getId());

        Assert.assertEquals(stock, stock1);
        Assert.assertEquals(stock3, stock2);
        
    }
    
    @Test
    public void findAllStocks()
    {
        List<Stock> ret = Arrays.asList(stock1, stock2);
        when(stockDao.findAll()).thenReturn(ret);

        List<Stock> stocks = stockService.getAllStocks();

        Assert.assertEquals(2, stocks.size());
        Assert.assertTrue(stocks.contains(stock1));
        Assert.assertTrue(stocks.contains(stock2));
    }
    
    @Test
    public void addPhone(){
        
        Phone phone = new Phone();
        Assert.assertTrue(!stock1.getPhones().contains(phone));
       
        when(phoneDao.findById(phone.getId())).thenReturn(phone);
        when(stockDao.findById(stock1.getId())).thenReturn(stock1);
        
        stockService.addPhone(stock1.getId(), phone.getId());
        
        Assert.assertTrue(stock1.getPhones().contains(phone));
    }
    
    @Test
    public void removePhone(){
        
        Phone phone = new Phone();
        Assert.assertTrue(!stock1.getPhones().contains(phone));
       
        when(phoneDao.findById(phone.getId())).thenReturn(phone);
        when(stockDao.findById(stock1.getId())).thenReturn(stock1);
        
        stockService.addPhone(stock1.getId(), phone.getId());
        
        Assert.assertTrue(stock1.getPhones().contains(phone));
        
        stockService.removePhone(stock1.getId(), phone.getId());
        
        Assert.assertTrue(!stock1.getPhones().contains(phone));
    }
}
