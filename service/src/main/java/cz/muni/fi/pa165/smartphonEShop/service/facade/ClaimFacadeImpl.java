package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.ClaimDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import cz.muni.fi.pa165.smartphonEShop.facade.ClaimFacade;
import cz.muni.fi.pa165.smartphonEShop.service.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by Roman Nahalka
 * Class represents: Implementation of ClaimFacade.
 */

public class ClaimFacadeImpl implements ClaimFacade
{
    @Autowired
    private BeanMappingService bms;

    @Override
    public ClaimDTO findClaimById(Long id) {
        return null;
    }

    @Override
    public Collection<ClaimDTO> findClaimByOrderId(Long orderId) {
        return null;
    }

    @Override
    public Collection<ClaimDTO> findClaimByUserId(Long userId) {
        return null;
    }

    @Override
    public Collection<ClaimDTO> findClaimByClaimState(ClaimState claimState) {
        return null;
    }

    @Override
    public Collection<ClaimDTO> findClaimByClaimSolution(ClaimSolution claimSolution) {
        return null;
    }

    @Override
    public Collection<ClaimDTO> getAllClaims() {
        return null;
    }

    @Override
    public void registerClaim(Claim claim) {

    }
}
