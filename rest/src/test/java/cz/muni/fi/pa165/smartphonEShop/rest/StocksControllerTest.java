package cz.muni.fi.pa165.smartphonEShop.rest;

import cz.muni.fi.pa165.smartphonEShop.RootWebContext;
import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.StockFacade;
import cz.muni.fi.pa165.smartphonEShop.rest.controllers.StocksController;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Jakub Ondrusek
 * Class represents: Tests for stock mvc controller.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class StocksControllerTest {
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
        stockDTO.setName("stock1");
        //stockDTO.setPhones();
    }

    @Test
    public void getStocksTest() throws Exception {
        when(stockFacade.getAllStocks()).thenReturn(Collections.singletonList(stockDTO));

        this.mockMvc.perform(get("/stocks"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==111)].name").value("stock1"));
    }

    @Test
    public void getStockTest() throws Exception {
        when(stockFacade.findStockById(stockDTO.getId())).thenReturn(stockDTO);

        this.mockMvc.perform(get("/stocks/111"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value("stock1"));
    }
}

