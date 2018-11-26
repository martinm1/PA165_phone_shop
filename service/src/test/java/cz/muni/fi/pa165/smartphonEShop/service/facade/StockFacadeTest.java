package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PhoneDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.StockService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Stefan Holecko
 * Class represents: Tests for stockFacade class
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class StockFacadeTest extends AbstractTestNGSpringContextTests {


    @Mock
    private StockService stockService;

    @Mock
    private BeanMappingService bms;

    @InjectMocks
    private StockFacadeImpl stockFacade;

    private Stock stock1;
    private Stock stock2;

    private StockDTO stockDTO1;
    private StockDTO stockDTO2;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void testPrepare() {
        stock1 = new Stock();
        stock2 = new Stock();
        stockDTO1 = new StockDTO();
        stockDTO2 = new StockDTO();

        stock1.setId(111L);
        stock2.setId(222L);
        stockDTO1.setId(111L);
        stockDTO2.setId(222L);

        stock1.setName("Sklad1");
        stock2.setName("Sklad2");
        stockDTO1.setName("Sklad1");
        stockDTO2.setName("Sklad2");

        Phone phone1 = new Phone();
        PhoneDTO phoneDTO1 = new PhoneDTO();

        phone1.setId(21L);
        phoneDTO1.setId(21L);

        stock1.addPhone(phone1);
    }

    @Test
    public void findStockByIdTest() {
        when(stockService.findStockById(111L)).thenReturn(stock1);
        when(stockService.findStockById(222L)).thenReturn(stock2);
        when(bms.mapTo(stock1, StockDTO.class)).thenReturn(stockDTO1);
        when(bms.mapTo(stock2, StockDTO.class)).thenReturn(stockDTO2);

        Assert.assertEquals(stockDTO1, stockFacade.findStockById(111L));
        Assert.assertEquals(stockDTO2, stockFacade.findStockById(222L));
    }

    @Test
    public void findStockByNameTest() {
        when(stockService.findStockByName("Sklad1")).thenReturn(stock1);
        when(stockService.findStockByName("Sklad2")).thenReturn(stock2);
        when(bms.mapTo(stock1, StockDTO.class)).thenReturn(stockDTO1);
        when(bms.mapTo(stock2, StockDTO.class)).thenReturn(stockDTO2);

        Assert.assertEquals(stockDTO1, stockFacade.findStockByName("Sklad1"));
        Assert.assertEquals(stockDTO2, stockFacade.findStockByName("Sklad2"));
    }

    @Test
    public void findStockByAddressIdTest() {
        Address address1 = new Address();
        Address address2 = new Address();
        AddressDTO addressDTO1 = new AddressDTO();
        AddressDTO addressDTO2 = new AddressDTO();

        address1.setId(44L);
        address2.setId(66L);
        addressDTO1.setId(44L);
        addressDTO2.setId(66L);

        stock1.setAddress(address1);
        stock2.setAddress(address2);
        stockDTO1.setAddress(addressDTO1);
        stockDTO2.setAddress(addressDTO2);

        when(stockService.findStockByAddressId(44L)).thenReturn(stock1);
        when(stockService.findStockByAddressId(66L)).thenReturn(stock2);
        when(bms.mapTo(stock1, StockDTO.class)).thenReturn(stockDTO1);
        when(bms.mapTo(stock2, StockDTO.class)).thenReturn(stockDTO2);

        Assert.assertEquals(stockDTO1, stockFacade.findStockByAddressId(44L));
        Assert.assertEquals(stockDTO2, stockFacade.findStockByAddressId(66L));
    }

    @Test
    public void findStockByPhoneIdTest() {
        when(stockService.findStockByPhoneId(21L)).thenReturn(stock1);
        when(bms.mapTo(stock1, StockDTO.class)).thenReturn(stockDTO1);

        Assert.assertEquals(stockDTO1,stockFacade.findStockByPhoneId(21L));
    }

    @Test
    public void getAllStocksTest() {
        List<Stock> stocks = Arrays.asList(stock1, stock2);
        List<StockDTO> stockDTOS = Arrays.asList(stockDTO1, stockDTO2);

        when(stockService.findAllStocks()).thenReturn(stocks);
        when(bms.mapTo(stocks, StockDTO.class)).thenReturn(stockDTOS);

        Collection<StockDTO> stockDTOS1 = stockFacade.getAllStocks();

        Assert.assertEquals(2, stockDTOS1.size());
    }

    @Test
    public void createStockTest() {
        Stock stock = new Stock();

        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(111L);

        doAnswer(invocationOnMock ->
        {
            stock.setId(111L);
            return 111L;
        }).when(stockService).createStock(stock);

        when(bms.mapTo(stock, StockDTO.class)).thenReturn(stockDTO);

        Assert.assertNotNull(stockDTO.getId());
        Assert.assertEquals(111L, stockDTO.getId().longValue());
    }

    @Test
    public void addPhoneTest() {
        //TODO


    }

    @Test
    public void removePhoneTest() {
        //TODO
    }

}
