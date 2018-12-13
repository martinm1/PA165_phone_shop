package cz.muni.fi.pa165.smartphonEShop.rest;

import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.StockFacade;
import cz.muni.fi.pa165.smartphonEShop.mvc.controllers.StockController;
import cz.muni.fi.pa165.smartphonEShop.rest.controllers.PhonesController;
import cz.muni.fi.pa165.smartphonEShop.rest.controllers.StocksController;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Jakub Ondrusek
 * Class represents: Tests for stock mvc controller.
 */
@WebAppConfiguration
public class StockControllerTest {
    @Mock
    private StockFacade stockFacade;

    @Autowired
    @InjectMocks
    private StocksController stocksController;

    private StockDTO stockDTO;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(stocksController).setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @BeforeMethod
    public void testPrepare() {
        stockDTO = new StockDTO();
        stockDTO.setId(111L);
        //stockDTO.setAddress();
        //stockDTO.setName();
        //stockDTO.setPhones();
    }

    @Test
    public void testListAll() {

    }

    @Test
    public void testListByAdressId() {

    }

    @Test
    public void testListByName() {

    }

    @Test
    public void testView() {

    }

    @Test
    public void testNewStrock() {

    }
}

