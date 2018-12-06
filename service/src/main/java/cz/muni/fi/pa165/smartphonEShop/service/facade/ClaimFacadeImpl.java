package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.ClaimCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.ClaimDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import cz.muni.fi.pa165.smartphonEShop.facade.ClaimFacade;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.ClaimService;
import cz.muni.fi.pa165.smartphonEShop.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Created by Roman Nahalka
 * Class represents: Implementation of ClaimFacade
 * @author xnahalka
 */

public class ClaimFacadeImpl implements ClaimFacade
{
    @Autowired
    private ClaimService claimService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BeanMappingService bms;

    @Override
    public ClaimDTO findClaimById(Long id)
    {
        Claim claim = claimService.findClaimById(id);

        if(claim == null)
            return null;

        return bms.mapTo(claim, ClaimDTO.class);
    }

    @Override
    public Collection<ClaimDTO> findClaimByOrderId(Long orderId)
    {
        List<Claim> claims = claimService.findClaimByOrderId(orderId);

        return bms.mapTo(claims, ClaimDTO.class);
    }

    @Override
    public Collection<ClaimDTO> findClaimByUserId(Long userId)
    {
        List<Claim> claims = claimService.findClaimByUserId(userId);

        return bms.mapTo(claims, ClaimDTO.class);
    }

    @Override
    public Collection<ClaimDTO> findClaimByClaimState(ClaimState claimState)
    {
        List<Claim> claims = claimService.findClaimByClaimState(claimState);

        return bms.mapTo(claims, ClaimDTO.class);
    }

    @Override
    public Collection<ClaimDTO> findClaimByClaimSolution(ClaimSolution claimSolution)
    {
        List<Claim> claims = claimService.findClaimByClaimSolution(claimSolution);

        return bms.mapTo(claims, ClaimDTO.class);
    }

    @Override
    public Collection<ClaimDTO> getAllClaims()
    {
        List<Claim> claims = claimService.getAllClaims();

        return bms.mapTo(claims, ClaimDTO.class);
    }

    @Override

    public Long createClaim(ClaimCreateDTO claim)
    {
        Claim mappedClaim = new Claim();

        mappedClaim.setOrder(orderService.findOrderById(claim.getOrder().getId()));
        mappedClaim.setClaimState(ClaimState.CREATED);
        mappedClaim.setReasonOfClaim(claim.getReasonOfClaim());
        mappedClaim.setWantedSolutionByCustomer(claim.getWantedSolutionByCustomer());

        return claimService.createClaim(bms.mapTo(claim, Claim.class));
    }

    @Override
    public void acceptClaim(Long id)
    {

    }

    @Override
    public void rejectClaim(Long id)
    {

    }
}
