package cz.muni.fi.pa165.smartphonEShop.rest;

import cz.muni.fi.pa165.smartphonEShop.RootWebContext;
import cz.muni.fi.pa165.smartphonEShop.dto.ClaimDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import cz.muni.fi.pa165.smartphonEShop.facade.ClaimFacade;
import cz.muni.fi.pa165.smartphonEShop.rest.controllers.ClaimsController;
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
 * Created by Stefan Holecko
 * Class represents: Tests for claims rest controller.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class ClaimsControllerTest {
    @Mock
    private ClaimFacade claimFacade;

    @Autowired
    @InjectMocks
    private ClaimsController claimsController;

    private MockMvc mockMvc;

    private ClaimDTO claimDTO;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(claimsController).setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @BeforeMethod
    public void testPrepare() {
        claimDTO = new ClaimDTO();

        claimDTO.setId(111L);
        claimDTO.setClaimState(ClaimState.CREATED);
        claimDTO.setReasonOfClaim("nefunguje");
        claimDTO.setTechnicalReport("funguje");
        claimDTO.setWantedSolutionByCustomer(ClaimSolution.MONEY);
    }

    @Test
    public void getClaimsTest() throws Exception {
        when(claimFacade.getAllClaims()).thenReturn(Collections.singletonList(claimDTO));

        this.mockMvc.perform(get("/claims"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==111)].reasonOfClaim").value("nefunguje"));
    }
}
