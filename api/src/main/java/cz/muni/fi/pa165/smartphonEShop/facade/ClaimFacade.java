package cz.muni.fi.pa165.smartphonEShop.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.ClaimDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;

import java.util.Collection;
import java.util.List;

/**
 * Created by Stefan Holecko
 * Class represents: ClaimFacade interface
 */

public interface ClaimFacade {

    /**
     * Find claim with specific id
     * @param id primary key for claim
     * @return claim with given id.
     */
    ClaimDTO findClaimById(Long id);

    /**
     * Find claims with specific orderId
     * @param orderId of the  claim
     * @return Collection of claims with given orderId.
     */
    Collection<ClaimDTO> findClaimByOrderId(Long orderId);

    /**
     * Find claims with specific userId
     * @param userId of the  claim
     * @return Collection of claims with given userId.
     */
    Collection<ClaimDTO> findClaimByUserId(Long userId);

    /**
     * Find claims with specific claimState
     * @param claimState of the  claim
     * @return Collection of claims with given claimState.
     */
    Collection<ClaimDTO> findClaimByClaimState(ClaimState claimState);

    /**
     * Find claims with specific claimSolution
     * @param claimSolution of the  claim
     * @return Collection of claims with given claimSolution.
     */
    Collection<ClaimDTO> findClaimByClaimSolution(ClaimSolution claimSolution);

    /**
     * Find all claims.
     * @return Collection of all claims.
     */
    Collection<ClaimDTO> getAllClaims();

    /**
     * Register new claim to system.
     * @param claim New claim.
     */
    void registerStock(ClaimDTO claim);

}
