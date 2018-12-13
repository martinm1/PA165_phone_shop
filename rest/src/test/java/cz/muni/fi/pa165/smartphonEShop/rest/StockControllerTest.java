package cz.muni.fi.pa165.smartphonEShop.rest;

import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.StockFacade;
import cz.muni.fi.pa165.smartphonEShop.mvc.controllers.StockController;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Jakub Ondrusek
 * Class represents: Tests for stock mvc controller.
 */
@WebAppConfiguration
public class StockControllerTest {
    @Mock
    private StockFacade stockFacade;

    private StockDTO stockDTO;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);

        StockController stockController = new StockController();
        stockController.setStockFacade(stockFacade);

        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @BeforeMethod
    public void testPrepare() {
        stockDTO = new StockDTO();
        stockDTO.setId(111L);
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

