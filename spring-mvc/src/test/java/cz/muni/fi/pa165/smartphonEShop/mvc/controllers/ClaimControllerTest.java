package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.ClaimDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import cz.muni.fi.pa165.smartphonEShop.facade.ClaimFacade;
import java.util.Collections;
import java.util.List;

import cz.muni.fi.pa165.smartphonEShop.facade.OrderFacade;
import org.mockito.Mock;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author martin
 */
@WebAppConfiguration
public class ClaimControllerTest {
    @Mock
    private ClaimFacade claimFacade;

    @Mock
    private OrderFacade orderFacade;

    private ClaimDTO claimDTO;

    private MockMvc mockMvc;
    
    
    @BeforeClass
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        ClaimController claimController = new ClaimController();
        claimController.setClaimFacade(claimFacade);
        claimController.setOrderFacade(orderFacade);

        mockMvc = MockMvcBuilders.standaloneSetup(claimController).build();
    }
    
    @BeforeMethod
    public void testPrepare()
    {
        claimDTO = new ClaimDTO();
        claimDTO.setId(10L);
    }
    
    @Test
    public void listByStateTest() throws Exception
    {
        List<ClaimDTO> claims = Collections.singletonList(claimDTO);

        when(claimFacade.findClaimByClaimState(ClaimState.ACCEPTED)).thenReturn(claims);

        this.mockMvc.perform(get("/claim/list/state/accepted")
            .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(model().attribute("claims", claims))
            .andExpect(forwardedUrl("claim/list"));
    }
    
    @Test
    public void nonExistingStateTest() throws Exception
    {
        this.mockMvc.perform(get("/claim/list/state/acc")
            .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("alert_danger"))
            .andExpect(forwardedUrl("claim/list"));
    }
    
    @Test
    public void listBySolutionTest() throws Exception
    {
        List<ClaimDTO> claims = Collections.singletonList(claimDTO);

        when(claimFacade.findClaimByClaimSolution(ClaimSolution.MONEY)).thenReturn(claims);

        this.mockMvc.perform(get("/claim/list/solution/money")
            .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(model().attribute("claims", claims))
            .andExpect(forwardedUrl("claim/list"));
    }
    
    @Test
    public void nonExistingSolutionTest() throws Exception
    {
        this.mockMvc.perform(get("/claim/list/solution/acc")
            .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("alert_danger"))
            .andExpect(forwardedUrl("claim/list"));
    }

    @Test
    public void listAllTest() throws Exception
    {
        List<ClaimDTO> claims = Collections.singletonList(claimDTO);

        when(claimFacade.getAllClaims()).thenReturn(claims);

        this.mockMvc.perform(get("/claim/list/all")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("claims", claims))
                .andExpect(forwardedUrl("claim/list"));
    }
    
    @Test
    public void listByUserIdTest() throws Exception
    {
        List<ClaimDTO> claims = Collections.singletonList(claimDTO);

        when(claimFacade.findClaimByUserId(5L)).thenReturn(claims);

        this.mockMvc.perform(get("/claim/list/byPerson?personId=5")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("claims", claims))
                .andExpect(forwardedUrl("claim/list"));
    }
    
    @Test
    public void listByOrderIdTest() throws Exception
    {
        List<ClaimDTO> claims = Collections.singletonList(claimDTO);

        when(claimFacade.findClaimByOrderId(5L)).thenReturn(claims);

        this.mockMvc.perform(get("/claim/list/byOrder?orderId=5")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("claims", claims))
                .andExpect(forwardedUrl("claim/list"));
    }
    
    @Test
    public void newClaimTest() throws Exception
    {
        when(orderFacade.findOrderById(any())).thenReturn(new OrderDTO());

        this.mockMvc.perform(get("/claim/new/5")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("claimCreate"))
                .andExpect(forwardedUrl("claim/new"));
    }
    
    @Test
    public void viewTest() throws Exception
    {
        when(claimFacade.findClaimById(claimDTO.getId())).thenReturn(claimDTO);

        this.mockMvc.perform(get("/claim/view/10")
        .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("claim", claimDTO))
                .andExpect(forwardedUrl("claim/view"));
    }
    
    @Test
    public void acceptTest() throws Exception
    {
        claimDTO.setClaimState(ClaimState.CREATED);

        doAnswer(InvocationOnMock ->
        {
            claimDTO.setClaimState(ClaimState.ACCEPTED);
            return 10L;
        }).when(claimFacade).acceptClaim(claimDTO.getId());

        this.mockMvc.perform(post("/claim/accept/10")
        .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("alert_success"));

        Assert.assertEquals(claimDTO.getClaimState(), ClaimState.ACCEPTED);
    }

    @Test
    public void rejectTest() throws Exception
    {
        claimDTO.setClaimState(ClaimState.CREATED);

        doAnswer(InvocationOnMock ->
        {
            claimDTO.setClaimState(ClaimState.REJECTED);
            return 10L;
        }).when(claimFacade).rejectClaim(claimDTO.getId());

        this.mockMvc.perform(post("/claim/reject/10")
        .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("alert_success"));

        Assert.assertEquals(claimDTO.getClaimState(), ClaimState.REJECTED);
    }
}
