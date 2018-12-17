package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.StockFacade;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        StockController stockController = new StockController();
        stockController.setStockFacade(stockFacade);

        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @BeforeMethod
    public void testPrepare()
    {
        stockDTO = new StockDTO();
        stockDTO.setId(33L);
        stockDTO.setName("niceName");
        AddressDTO address = new AddressDTO();
        address.setId(22L);
        stockDTO.setAddress(address);
    }
    @Test
    public void testView () throws Exception {
        when(stockFacade.findStockById(stockDTO.getId())).thenReturn(stockDTO);

        this.mockMvc.perform(get("/stock/view/33")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("stock", stockDTO))
                .andExpect(forwardedUrl("stock/view"));

    }

    @Test
    public void testNewStock () throws Exception{
        this.mockMvc.perform(get("/stock/new")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("stockCreate"))
                .andExpect(forwardedUrl("stock/new"));
    }

    @Test
    public void testListAll () throws Exception {
        List<StockDTO> stocks = Collections.singletonList(stockDTO);

        when(stockFacade.getAllStocks()).thenReturn(stocks);
        this.mockMvc.perform(get("/stock/list/all")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("stocksAll", stocks))
                .andExpect(forwardedUrl("stock/list"));
    }

    @Test
    public void testListName () throws Exception{
        when(stockFacade.findStockByName("niceName")).thenReturn(stockDTO);

        this.mockMvc.perform(get("/stock/list/byName?name=niceName")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("stockByName", stockDTO))
                .andExpect(forwardedUrl("stock/list"));

    }

    @Test
    public void testListByAddressId () throws Exception{
        when(stockFacade.findStockByAddressId(22L)).thenReturn(stockDTO);

        this.mockMvc.perform(get("/stock/list/byAddress?addressId=22")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("stockByAddressId", stockDTO))
                .andExpect(forwardedUrl("stock/list"));
    }




}
