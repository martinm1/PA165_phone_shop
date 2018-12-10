package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.OrderCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Roman Nahalka
 * Class represents: Tests for order mvc controller.
 * @author xnahalka
 */
@WebAppConfiguration
public class OrderControllerTest
{
    @Mock
    private OrderFacade orderFacade;

    private OrderDTO orderDTO;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        OrderController orderController = new OrderController();
        orderController.setOrderFacade(orderFacade);

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @BeforeMethod
    public void testPrepare()
    {
        orderDTO = new OrderDTO();
        orderDTO.setId(10L);
    }

    @Test
    public void testList() throws Exception
    {
        List<OrderDTO> orders = Collections.singletonList(orderDTO);

        when(orderFacade.findOrdersByOrderState(OrderState.ACCEPTED)).thenReturn(orders);

        this.mockMvc.perform(get("/order/list/accepted")
            .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(model().attribute("orders", orders))
            .andExpect(forwardedUrl("order/list"));
    }

    @Test
    public void newTest() throws Exception
    {
        this.mockMvc.perform(get("/order/new")
        .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("orderCreate"))
                .andExpect(forwardedUrl("order/new"));
    }
}
