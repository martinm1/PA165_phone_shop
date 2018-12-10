package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

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
}
