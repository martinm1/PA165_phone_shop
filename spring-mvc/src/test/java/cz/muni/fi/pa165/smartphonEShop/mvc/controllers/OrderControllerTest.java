package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.OrderCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.exceptions.EshopServiceException;
import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.print.attribute.standard.Media;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void viewTest() throws Exception
    {
        when(orderFacade.findOrderById(orderDTO.getId())).thenReturn(orderDTO);

        this.mockMvc.perform(get("/order/view/10")
        .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("order", orderDTO))
                .andExpect(forwardedUrl("order/view"));
    }

    @Test
    public void createTest() throws Exception //TODO
    {
        OrderCreateDTO createDTO = new OrderCreateDTO();

        when(orderFacade.createOrder(createDTO)).thenReturn(10L);

        this.mockMvc.perform(post("/order/create")
        .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void cancelTest() throws Exception
    {
        orderDTO.setState(OrderState.CREATED);

        doAnswer(InvocationOnMock ->
        {
            orderDTO.setState(OrderState.CANCELED);
            return 10L;
        }).when(orderFacade).cancelOrder(orderDTO.getId());

        this.mockMvc.perform(post("/order/cancel/10")
        .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("alert_success"));

        Assert.assertEquals(orderDTO.getState(), OrderState.CANCELED);
    }

    @Test
    public void notExistsCancelTest() throws Exception
    {
        doAnswer(InvocationOnMock ->
        {
            throw new EshopServiceException();
        }).when(orderFacade).cancelOrder(5L);

        this.mockMvc.perform(post("/order/cancel/5")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("alert_danger"));
    }

    @Test
    public void finishTest() throws Exception
    {
        orderDTO.setState(OrderState.CREATED);

        doAnswer(InvocationOnMock ->
        {
            orderDTO.setState(OrderState.FINISHED);
            return 10L;
        }).when(orderFacade).finishOrder(orderDTO.getId());

        this.mockMvc.perform(post("/order/finish/10")
        .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("alert_success"));

        Assert.assertEquals(orderDTO.getState(), OrderState.FINISHED);
    }

    @Test
    public void notExistsFinishTest() throws Exception
    {
        doAnswer(InvocationOnMock ->
        {
            throw new EshopServiceException();
        }).when(orderFacade).finishOrder(5L);

        this.mockMvc.perform(post("/order/finish/5")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("alert_danger"));
    }

    @Test
    public void acceptTest() throws Exception
    {
        orderDTO.setState(OrderState.CREATED);

        doAnswer(InvocationOnMock ->
        {
            orderDTO.setState(OrderState.ACCEPTED);
            return 10L;
        }).when(orderFacade).acceptOrder(orderDTO.getId());

        this.mockMvc.perform(post("/order/accept/10")
        .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("alert_success"));

        Assert.assertEquals(orderDTO.getState(), OrderState.ACCEPTED);
    }

    @Test
    public void notExistsAcceptTest() throws Exception
    {
        doAnswer(InvocationOnMock ->
        {
            throw new EshopServiceException();
        }).when(orderFacade).acceptOrder(5L);

        this.mockMvc.perform(post("/order/accept/5")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("alert_danger"));
    }
}
