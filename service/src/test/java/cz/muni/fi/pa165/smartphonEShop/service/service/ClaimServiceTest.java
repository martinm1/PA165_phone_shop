package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.ClaimDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Roman Nahalka
 * Class represents: Tests for ClaimService.
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ClaimServiceTest extends AbstractTestNGSpringContextTests
{
    @Mock
    private ClaimDao claimDao;

    @Autowired
    @InjectMocks
    private ClaimService claimService;

    private Claim claim1;
    private Claim claim2;
    private Claim claim3;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void testPrepare()
    {
        claim1 = new Claim();
        claim2 = new Claim();
        claim3 = new Claim();

        claim1.setId(10L);
        claim2.setId(20L);
        claim3.setId(30L);

        claim1.setClaimState(ClaimState.CREATED);
        claim2.setClaimState(ClaimState.ACCEPTED);
        claim3.setClaimState(ClaimState.REJECTED);

        claim1.setWantedSolutionByCustomer(ClaimSolution.REPAIR);
        claim2.setWantedSolutionByCustomer(ClaimSolution.REPAIR);
        claim3.setWantedSolutionByCustomer(ClaimSolution.MONEY);
    }

    @Test
    public void getAllClaims()
    {
        List<Claim> ret = Arrays.asList(claim1, claim2, claim3);
        when(claimDao.findAll()).thenReturn(ret);

        List<Claim> claims = claimService.getAllClaims();

        Assert.assertEquals(3, claims.size());
        Assert.assertTrue(claims.contains(claim1));
        Assert.assertTrue(claims.contains(claim2));
        Assert.assertTrue(claims.contains(claim3));
    }

    @Test
    public void findClaimById()
    {
        when(claimDao.findById(claim1.getId())).thenReturn(claim1);
        when(claimDao.findById(claim2.getId())).thenReturn(claim2);
        when(claimDao.findById(claim3.getId())).thenReturn(claim3);

        Claim testClaim1 = claimService.findClaimById(claim1.getId());
        Claim testClaim2 = claimService.findClaimById(claim2.getId());
        Claim testClaim3 = claimService.findClaimById(claim3.getId());

        Assert.assertEquals(testClaim1, claim1);
        Assert.assertEquals(testClaim2, claim2);
        Assert.assertEquals(testClaim3, claim3);
    }

    @Test
    public void createClaim()
    {
        Claim claim = new Claim();

        doAnswer(invocationOnMock ->
        {
            claim.setId(40L);
            return 40L;
        }).when(claimDao).create(claim);

        claimService.createClaim(claim);
        Assert.assertNotNull(claim.getId());
        Assert.assertEquals(40L, claim.getId().longValue());
    }

    @Test
    public void findClaimByOrderId()
    {
        Long orderId = 1L;
        Long orderId1 = 2L;

        List<Claim> ret = Arrays.asList(claim2, claim3);
        when(claimDao.findClaimByOrderId(orderId)).thenReturn(Collections.singletonList(claim1));
        when(claimDao.findClaimByOrderId(orderId1)).thenReturn(ret);

        List<Claim> claims = claimService.findClaimByOrderId(orderId);

        Assert.assertEquals(1, claims.size());
        Assert.assertTrue(claims.contains(claim1));

        claims = claimService.findClaimByOrderId(orderId1);

        Assert.assertEquals(2, claims.size());
        Assert.assertTrue(claims.contains(claim2));
        Assert.assertTrue(claims.contains(claim3));
    }

    @Test
    public void findClaimByUserId()
    {
        Long userId = 1L;
        Long userId1 = 2L;

        List<Claim> ret = Arrays.asList(claim1, claim2);
        when(claimDao.findClaimByUserId(userId)).thenReturn(Collections.singletonList(claim3));
        when(claimDao.findClaimByUserId(userId1)).thenReturn(ret);

        List<Claim> claims = claimService.findClaimByUserId(userId);

        Assert.assertEquals(1, claims.size());
        Assert.assertTrue(claims.contains(claim3));

        claims = claimService.findClaimByUserId(userId1);

        Assert.assertEquals(2, claims.size());
        Assert.assertTrue(claims.contains(claim1));
        Assert.assertTrue(claims.contains(claim2));
    }

    @Test
    public void findClaimByClaimState()
    {
        when(claimDao.findClaimByClaimState(ClaimState.CREATED)).thenReturn(Collections.singletonList(claim1));
        when(claimDao.findClaimByClaimState(ClaimState.ACCEPTED)).thenReturn(Collections.singletonList(claim2));
        when(claimDao.findClaimByClaimState(ClaimState.REJECTED)).thenReturn(Collections.singletonList(claim3));

        List<Claim> claims = claimService.findClaimByClaimState(ClaimState.CREATED);

        Assert.assertEquals(1, claims.size());
        Assert.assertTrue(claims.contains(claim1));

        claims = claimService.findClaimByClaimState(ClaimState.ACCEPTED);

        Assert.assertEquals(1, claims.size());
        Assert.assertTrue(claims.contains(claim2));

        claims = claimService.findClaimByClaimState(ClaimState.REJECTED);

        Assert.assertEquals(1, claims.size());
        Assert.assertTrue(claims.contains(claim3));
    }

    @Test
    public void findClaimByClaimSolution()
    {
        List<Claim> ret = Arrays.asList(claim1, claim2);
        when(claimDao.findClaimByClaimSolution(ClaimSolution.REPAIR)).thenReturn(ret);
        when(claimDao.findClaimByClaimSolution(ClaimSolution.MONEY)).thenReturn(Collections.singletonList(claim3));

        List<Claim> claims = claimService.findClaimByClaimSolution(ClaimSolution.REPAIR);

        Assert.assertEquals(2, claims.size());
        Assert.assertTrue(claims.contains(claim1));
        Assert.assertTrue(claims.contains(claim2));

        claims = claimService.findClaimByClaimSolution(ClaimSolution.MONEY);

        Assert.assertEquals(1, claims.size());
        Assert.assertTrue(claims.contains(claim3));
    }
}
