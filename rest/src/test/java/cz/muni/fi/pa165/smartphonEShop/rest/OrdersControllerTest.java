package cz.muni.fi.pa165.smartphonEShop.rest;

import cz.muni.fi.pa165.smartphonEShop.RootWebContext;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
import cz.muni.fi.pa165.smartphonEShop.rest.controllers.OrdersController;
import java.time.LocalDate;
import java.util.Collections;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
/**
 *
 * @author martin
 */
@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class OrdersControllerTest {
    @Mock
    private OrderFacade orderFacade;

    @Autowired
    @InjectMocks
    private OrdersController ordersController;

    private MockMvc mockMvc;

    private OrderDTO orderDTO;
    
    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(ordersController).setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @BeforeMethod
    public void testPrepare() {
        orderDTO = new OrderDTO();

        orderDTO.setId(111L);
        orderDTO.setOrderDate(LocalDate.ofYearDay(2018, 10));
        orderDTO.setState(OrderState.OUTDATED);
    }
    
    @Test
    public void getOrdersTest() throws Exception {
        when(orderFacade.getAllOrders()).thenReturn(Collections.singletonList(orderDTO));

        this.mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==111)].state").value(OrderState.OUTDATED.toString()));
    }

    @Test
    public void getOrderTest() throws Exception {
        when(orderFacade.findOrderById(orderDTO.getId())).thenReturn(orderDTO);

        this.mockMvc.perform(get("/orders/111"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.state").value(OrderState.OUTDATED.toString()));
    }
}
