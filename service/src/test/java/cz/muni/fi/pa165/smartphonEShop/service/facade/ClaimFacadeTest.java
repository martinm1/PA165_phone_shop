package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.ClaimDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.ClaimService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Jakub Ondrusek
 * Class represents: Tests for claimFacade class
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ClaimFacadeTest extends AbstractTestNGSpringContextTests {
    @Mock
    private ClaimService claimService;

    @Mock
    private BeanMappingService bms;

    @InjectMocks
    private ClaimFacadeImpl claimFacade;

    private Claim claim1;
    private Claim claim2;

    private ClaimDTO claimDTO1;
    private ClaimDTO claimDTO2;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void testPrepare() {
        claim1 = new Claim();
        claim2 = new Claim();

        claim1.setId(1L);
        claim2.setId(2L);

        claim1.setClaimState(ClaimState.CREATED);
        claim2.setClaimState(ClaimState.ACCEPTED);

        claim1.setReasonOfClaim("bad days happen");
        claim2.setReasonOfClaim("good days never happen");

        claim1.setWantedSolutionByCustomer(ClaimSolution.MONEY);
        claim2.setWantedSolutionByCustomer(ClaimSolution.REPAIR);

        claimDTO1 = new ClaimDTO();
        claimDTO2 = new ClaimDTO();

        claimDTO1.setId(1L);
        claimDTO2.setId(2L);

        claimDTO1.setClaimState(ClaimState.CREATED);
        claimDTO2.setClaimState(ClaimState.ACCEPTED);

        claimDTO1.setReasonOfClaim("bad days happen");
        claimDTO2.setReasonOfClaim("good days never happen");

        claimDTO1.setWantedSolutionByCustomer(ClaimSolution.MONEY);
        claimDTO2.setWantedSolutionByCustomer(ClaimSolution.REPAIR);

    }

    @Test
    public void getAllClaims() {
        List<Claim> retClaims = Arrays.asList(claim1, claim2);

        when(claimService.getAllClaims()).thenReturn(retClaims);
        when(bms.mapTo(retClaims, ClaimDTO.class)).thenReturn(Arrays.asList(claimDTO1,claimDTO2));

        Collection<ClaimDTO> claims = claimFacade.getAllClaims();
        Assert.assertEquals(2,claims.size());
    }

    @Test
    public void findClaimById() {
        when(claimService.findClaimById(1L)).thenReturn(claim1);
        when(claimService.findClaimById(2L)).thenReturn(claim2);
        when(bms.mapTo(claim1, ClaimDTO.class)).thenReturn(claimDTO1);
        when(bms.mapTo(claim2, ClaimDTO.class)).thenReturn(claimDTO2);

        Assert.assertEquals(claimDTO1, claimFacade.findClaimById(1L));
        Assert.assertEquals(claimDTO2, claimFacade.findClaimById(2L));
    }

    @Test
    public void findClaimByOrderId() {
        when(claimService.findClaimByOrderId(1L)).thenReturn(Collections.singletonList(claim1));
        when(claimService.findClaimByOrderId(2L)).thenReturn(Collections.singletonList(claim2));

        when(bms.mapTo(Collections.singletonList(claim1), ClaimDTO.class)).thenReturn(Collections.singletonList(claimDTO1));
        when(bms.mapTo(Collections.singletonList(claim2), ClaimDTO.class)).thenReturn(Collections.singletonList(claimDTO2));

        Collection<ClaimDTO> foundClaims = claimFacade.findClaimByOrderId(1l);
        Assert.assertEquals(1,foundClaims.size());
        Assert.assertTrue(foundClaims.contains(claimDTO1));

        foundClaims = claimFacade.findClaimByOrderId(2l);
        Assert.assertEquals(1,foundClaims.size());
        Assert.assertTrue(foundClaims.contains(claimDTO2));
    }
    @Test
    public void findClaimByUserId() {
        when(claimService.findClaimByUserId(1L)).thenReturn(Collections.singletonList(claim1));
        when(claimService.findClaimByUserId(2L)).thenReturn(Collections.singletonList(claim2));

        when(bms.mapTo(Collections.singletonList(claim1), ClaimDTO.class)).thenReturn(Collections.singletonList(claimDTO1));
        when(bms.mapTo(Collections.singletonList(claim2), ClaimDTO.class)).thenReturn(Collections.singletonList(claimDTO2));

        Collection<ClaimDTO> foundClaims = claimFacade.findClaimByUserId(1l);
        Assert.assertEquals(1,foundClaims.size());
        Assert.assertTrue(foundClaims.contains(claimDTO1));

        foundClaims = claimFacade.findClaimByUserId(2l);
        Assert.assertEquals(1,foundClaims.size());
        Assert.assertTrue(foundClaims.contains(claimDTO2));
    }
    @Test
    public void findClaimByClaimState() {
        when(claimService.findClaimByClaimState(ClaimState.CREATED)).thenReturn(Collections.singletonList(claim1));
        when(claimService.findClaimByClaimState(ClaimState.ACCEPTED)).thenReturn(Collections.singletonList(claim2));

        when(bms.mapTo(Collections.singletonList(claim1), ClaimDTO.class)).thenReturn(Collections.singletonList(claimDTO1));
        when(bms.mapTo(Collections.singletonList(claim2), ClaimDTO.class)).thenReturn(Collections.singletonList(claimDTO2));

        Collection<ClaimDTO> foundClaims = claimFacade.findClaimByClaimState(ClaimState.CREATED);
        Assert.assertEquals(1,foundClaims.size());
        Assert.assertTrue(foundClaims.contains(claimDTO1));

        foundClaims = claimFacade.findClaimByClaimState(ClaimState.ACCEPTED);
        Assert.assertEquals(1,foundClaims.size());
        Assert.assertTrue(foundClaims.contains(claimDTO2));

    }

    @Test
    public void findClaimByClaimSolution() {
        when(claimService.findClaimByClaimSolution(ClaimSolution.MONEY)).thenReturn(Collections.singletonList(claim1));
        when(claimService.findClaimByClaimSolution(ClaimSolution.REPAIR)).thenReturn(Collections.singletonList(claim2));

        when(bms.mapTo(Collections.singletonList(claim1), ClaimDTO.class)).thenReturn(Collections.singletonList(claimDTO1));
        when(bms.mapTo(Collections.singletonList(claim2), ClaimDTO.class)).thenReturn(Collections.singletonList(claimDTO2));

        Collection<ClaimDTO> foundClaims = claimFacade.findClaimByClaimSolution(ClaimSolution.MONEY);
        Assert.assertEquals(1,foundClaims.size());
        Assert.assertTrue(foundClaims.contains(claimDTO1));

        foundClaims = claimFacade.findClaimByClaimSolution(ClaimSolution.REPAIR);
        Assert.assertEquals(1,foundClaims.size());
        Assert.assertTrue(foundClaims.contains(claimDTO2));

    }
    @Test
    public void createClaim() {
        Claim claim  = new Claim();

        ClaimDTO claimDTO = new ClaimDTO();
        claimDTO.setId(3L);

        doAnswer(invocationOnMock ->
        {
            claim.setId(3L);
            return 3L;
        }).when(claimService).createClaim(claim);

        when(bms.mapTo(claim, ClaimDTO.class)).thenReturn(claimDTO);

        Assert.assertNotNull(claimDTO.getId());
        Assert.assertEquals(3L, claimDTO.getId().longValue());
    }


}
